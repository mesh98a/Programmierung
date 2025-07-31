package domainpackage;

import domainpackage.dto.*;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Kuchenprodukt;
import observerpattern.Beobachter;
import observerpattern.Subjekt;
import verwaltung.Hersteller;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Automat implements Subjekt, Serializable {
    static final long serialVersionUID = 1L;

    private AbstractCake[] cakes;
    private int capacity;
    private Map<Hersteller, Integer> herstellerMap;
    private Set<Allergen> allergen;
    private transient List<Beobachter> observers;

    public Automat(int capacity) {
        this.capacity = capacity;
        this.cakes = new AbstractCake[capacity];
        this.herstellerMap = new HashMap<Hersteller, Integer>();
        this.allergen = new HashSet<Allergen>();
        this.observers = new ArrayList<>();
    }

    public synchronized int getCapacity() {
        return this.capacity;
    }


    public synchronized boolean insertCake(AbstractCake cake) {
        // https://schmidtdennis.hashnode.dev/validator-design-pattern
        if (cake == null) {
            return false;
        }
        ErrorCake error = CakeValidator.validate(cake);
        if (error != null) {
            return false;
        }
        Hersteller hersteller = cake.getHersteller();
        if (!this.herstellerMap.containsKey(hersteller)) {
            return false;
        }

        for (int i = 0; i < this.cakes.length; i++) {
            if (this.cakes[i] == null) {
                this.cakes[i] = cake;
                cake.setFachnummer(i);

                int count = this.herstellerMap.get(hersteller);
                this.herstellerMap.put(hersteller, count + 1);

                this.allergen.addAll(cake.getAllergene());

                notifyObservers();

                return true;
            }
        }

        return false;
    }


    public synchronized List<Kuchenprodukt> getListCake() {
        List<Kuchenprodukt> cakeList = new ArrayList<>();
        for (AbstractCake cake : cakes) {
            if (cake != null) {
                cakeList.add(cake);
            }
        }
        return cakeList;
    }

    public synchronized List<Kuchenprodukt> getListCake(KuchenTyp typ) {
        List<Kuchenprodukt> filterCakeList = Arrays.stream(this.cakes)
                .filter(Objects::nonNull) // https://beginnersbook.com/2017/10/java-8-filter-null-values-from-a-stream/
                .filter(k -> k.getKuchenTyp() == typ)
                .collect(Collectors.toList());

        return filterCakeList;
    }

    public synchronized Set<Allergen> getAllergen(boolean vorhandene) {
        if (!vorhandene) {
            Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);
            allAllergene.removeAll(this.allergen);
            return allAllergene;
        } else {
            return new HashSet<>(this.allergen);
        }
    }


    public synchronized boolean deleteCake(int id) {
        if (id < 0 || id >= cakes.length) {
            return false;
        }
        AbstractCake cakeToRemove = cakes[id];
        if (cakeToRemove == null) {
            return false;
        }
        Hersteller hersteller = cakeToRemove.getHersteller();

        int currentCount = herstellerMap.get(hersteller);
        herstellerMap.put(hersteller, currentCount - 1);


        Collection<Allergen> allergensToRemove = cakeToRemove.getAllergene();
        this.allergen.removeAll(allergensToRemove);
        cakes[id] = null;
        for (AbstractCake cake : this.cakes) {
            if (cake != null) {
                this.allergen.addAll(cake.getAllergene());
                break;
            }
        }

        notifyObservers();
        return true;

    }

    public synchronized boolean inspectCake(int id) {
        if (id < 0 || id >= cakes.length) {
            return false;
        }
        if (cakes[id] == null) {
            return false;
        }

        cakes[id].setInspektionsdatum(new Date());
        notifyObservers();
        return true;
    }

    public synchronized boolean insertHersteller(String herstellerName) {
        if (herstellerName == null || herstellerName.isEmpty()) {
            return false;
        }
        if (herstellerName.matches("\\d+")) {
            return false;
        }
        Hersteller hersteller = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(hersteller)) {
            return false;
        }
        this.herstellerMap.put(hersteller, 0);
        notifyObservers();
        return true;
    }

    public synchronized Map<Hersteller, Integer> getHerstellerMap() {
        return new HashMap<>(this.herstellerMap);
    }

    public synchronized boolean deleteHersteller(String herstellerName) {
        Hersteller toRemove = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(toRemove)) {
            this.herstellerMap.remove(toRemove);
            notifyObservers();
            return true;
        }
        return false;
    }

    @Override
    public void registerObserver(Beobachter o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Beobachter o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Beobachter o : observers) {
            o.update();
        }
    }

    public List<Beobachter> getObservers() {
        return new ArrayList<>(observers);
    }

    public synchronized boolean swapFachnummern(int fachnummer1, int fachnummer2) {
        AbstractCake cake1 = cakes[fachnummer1];
        AbstractCake cake2 = cakes[fachnummer2];

        int tempFachnummer = -1;

        cake1.setFachnummer(tempFachnummer);
        cake2.setFachnummer(fachnummer1);
        cake1.setFachnummer(fachnummer2);

        cakes[fachnummer1] = cake2;
        cakes[fachnummer2] = cake1;
        notifyObservers();

        return true;
    }

    public synchronized void copyFrom(Automat other) {
        if (other == null) {
            return;
        }
        this.capacity = other.capacity;

        this.cakes = Arrays.copyOf(other.cakes, other.cakes.length);

        this.herstellerMap = new HashMap<>(other.herstellerMap);

        this.allergen = new HashSet<>(other.allergen);
        notifyObservers();
    }

    public synchronized AutomatDTO createDTO() {
        AutomatDTO dto = new AutomatDTO();
        dto.setCapacity(this.capacity);

        List<HerstellerMapDTO> herstellerListe = this.getHerstellerMap().entrySet().stream()
                .map(entry -> new HerstellerMapDTO(
                        new HerstellerDTO(entry.getKey().getName()),
                        entry.getValue()))
                .collect(Collectors.toList());
        dto.setHerstellerListe(herstellerListe);

        List<String> allergene = this.getAllergen(true).stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        dto.setAllergene(allergene);

        List<CakeDTO> cakeDTOs = Arrays.stream(this.cakes)
                .filter(Objects::nonNull)
                .map(CakeDTOMapper::toDTO)
                .collect(Collectors.toList());
        dto.setCakes(cakeDTOs);
        notifyObservers();
        return dto;
    }

    public synchronized void restoreFromDTO(AutomatDTO automatDTO) {
        this.capacity = automatDTO.getCapacity();
        List<Kuchenprodukt> cakeList = new ArrayList<>();
        for (CakeDTO cake : automatDTO.getCakes()) {
            Kuchenprodukt k = CakeDTOMapper.toCake(cake);
            cakeList.add(k);
        }
        this.cakes = cakeList.toArray(new AbstractCake[automatDTO.getCapacity()]);

        this.herstellerMap = new HashMap<>();
        for (HerstellerMapDTO entry : automatDTO.getHerstellerListe()) {
            Hersteller hersteller = new HerstellerImpl(entry.getHersteller().getName());
            this.herstellerMap.put(hersteller, entry.getAnzahl());
        }
        this.allergen = new HashSet<>();
        for (String a : automatDTO.getAllergene()) {
            this.allergen.add(Allergen.valueOf(a));
        }
        notifyObservers();
    }


}
