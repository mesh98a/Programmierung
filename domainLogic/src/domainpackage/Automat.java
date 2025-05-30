package domainpackage;

import kuchen.Allergen;
import observerpattern.Beobachter;
import observerpattern.Subjekt;
import verwaltung.Hersteller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Automat implements Subjekt {
    private AbstractCake[] cakes;
    private int capacity;
    private Map<Hersteller, Integer> herstellerMap;
    private Set<Allergen> allergen;
    private List<Beobachter> observers;
    private Aktion letzteAktion;
    private Thread letzterThread;

    public Automat(int capacity) {
        this.capacity = capacity;
        this.cakes = new AbstractCake[capacity];
        this.herstellerMap = new HashMap<Hersteller, Integer>();
        this.allergen = new HashSet<Allergen>();
        this.observers = new ArrayList<>();
    }


    public synchronized boolean insertCake(AbstractCake cake) {
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

                this.letzteAktion = Aktion.EINFUEGEN;
                notifyObservers();

                return true;
            }
        }

        return false;
    }


    public synchronized List<AbstractCake> displayListCake() {
        List<AbstractCake> cakeList = new ArrayList<>();
        for (AbstractCake cake : cakes) {
            if (cake != null) {
                cakeList.add(cake);
            }
        }
        return cakeList;
    }

    public synchronized List<AbstractCake> displayListCake(KuchenTyp typ) {
        List<AbstractCake> filterCakeList = Arrays.stream(this.cakes)
                .filter(Objects::nonNull) // https://beginnersbook.com/2017/10/java-8-filter-null-values-from-a-stream/
                .filter(k -> k.getKuchenTyp() == typ)
                .collect(Collectors.toList());

        return filterCakeList;
    }

    public synchronized Set<Allergen> displayAllergen() {
        return new HashSet<>(this.allergen);
    }
    public synchronized Set<Allergen> displaykeineAllergen() {
        Set<Allergen> allergene = new HashSet<>(this.allergen);
        Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);

        Set<Allergen> keineAllergene = new HashSet<>(allAllergene);
        keineAllergene.removeAll(allergene);
        return keineAllergene;
    }


    public synchronized boolean deleteCake(int id) {
        if (id < 0 || id >= cakes.length) {
            return false;
        }
        if (cakes[id] == null) {
            return false;
        }
        cakes[id] = null;
        this.letzteAktion = Aktion.LOESCHEN;
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
        this.letzteAktion = Aktion.INSPEKTION;
        notifyObservers();
        return true;
    }

    public synchronized boolean insertHersteller(String herstellerName) {
        Hersteller hersteller = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(hersteller)) {
            return false;
        }
        this.herstellerMap.put(hersteller, 0);
        return true;
    }

    public synchronized Map<Hersteller, Integer> getHerstellerMap() {
        return new HashMap<>(this.herstellerMap);
    }

    public synchronized boolean deleteHersteller(String herstellerName) {
        Hersteller toRemove = new HerstellerImpl(herstellerName);
        if (this.herstellerMap.containsKey(toRemove)) {
            this.herstellerMap.remove(toRemove);
            return true;
        }
        return true;
    }

    public synchronized int getFreeCapacity() {
        int counter = 0;
        for (AbstractCake cake : cakes) {
            if (cake != null) {
                counter++;
            }
        }

        return this.capacity - counter;
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
        this.letzterThread = Thread.currentThread();
        for (Beobachter o : observers) {
            o.update();
        }
    }

    public synchronized Aktion getLetzteAktion() {
        return this.letzteAktion;
    }
    public synchronized Thread getLetzterThread() {
        return this.letzterThread;
    }
}
