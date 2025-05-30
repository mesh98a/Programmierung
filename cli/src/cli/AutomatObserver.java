package cli;

import domainpackage.Automat;
import observerpattern.Beobachter;

public class AutomatObserver implements Beobachter {
    private Automat observable;
    public AutomatObserver(Automat observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }

    @Override
    public void update() {
        int belegtePlaetze = observable.displayListCake().size();
        int kapazitaet = belegtePlaetze + observable.getFreeCapacity();
        double auslastung = (double) belegtePlaetze / kapazitaet;
        if (auslastung > 0.9) {
            System.out.println("Kapazität über 90%");
        }
    }

}
