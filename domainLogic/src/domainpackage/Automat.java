package domainpackage;

import kuchen.Allergen;
import verwaltung.Hersteller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Automat {
    private AbstractCake[] cakes;
    private int capacity;
    private Map<Hersteller, Integer> herstellerMap;
    private Set<Allergen> allergen;

    public Automat(int capacity) {
        this.capacity = capacity;
        this.cakes = new AbstractCake[capacity];
        this.herstellerMap = new HashMap<Hersteller, Integer>();
        this.allergen = new HashSet<Allergen>();
    }


    public boolean insertCake(AbstractCake cake) {
        // https://schmidtdennis.hashnode.dev/validator-design-pattern
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

                cake.setEinfuegedatum(LocalDateTime.now());

                int count = this.herstellerMap.get(hersteller);
                this.herstellerMap.put(hersteller, count + 1);

                this.allergen.addAll(cake.getAllergene());

                return true;
            }
        }

        return false;
    }


    public List<AbstractCake> displayListCake() {
        List<AbstractCake> cakeList = new ArrayList<>();
        for (AbstractCake cake : cakes) {
            if (cake != null) {
                cakeList.add(cake);
            }
        }
        return cakeList;
    }

    public List<AbstractCake> displayListCake(KuchenTyp typ) {
        List<AbstractCake> filterCakeList = Arrays.stream(this.cakes)
                .filter(Objects::nonNull) // https://beginnersbook.com/2017/10/java-8-filter-null-values-from-a-stream/
                .filter(k -> k.getKuchenTyp() == typ)
                .collect(Collectors.toList());

        return filterCakeList;
    }

    public Set<Allergen> displayAllergen() {
        return new HashSet<>(this.allergen);
    }


    public boolean deleteCake(int id) {
        if (id < 0 || id >= cakes.length) {
            return false;
        }
        if (cakes[id] == null) {
            return false;
        }
        cakes[id] = null;
        return true;
    }

    public boolean inspectCake(int id, Date date) {
        if (id < 0 || id >= cakes.length) {
            return false;
        }
        if (cakes[id] == null) {
            return false;
        }

        cakes[id].setInspektionsdatum(date);
        return true;
    }

    public boolean insertHersteller(String herstellerName) {
        Hersteller hersteller = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(hersteller)) {
            return false;
        }
        this.herstellerMap.put(hersteller, 0);
        return true;
    }

    public Map<Hersteller, Integer> getHerstellerMap() {
        return new HashMap<>(this.herstellerMap);
    }

    public boolean deleteHersteller(String herstellerName) {
        Hersteller toRemove = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(toRemove)) {
            this.herstellerMap.remove(toRemove);
            return true;
        }
        return true;
    }

    public int getFreeCapacity() {
        int counter = 0;
        for (AbstractCake cake : cakes) {
            if (cake != null) {
                counter++;
            }
        }
        int free = this.capacity - counter;

        return free;
    }
}
