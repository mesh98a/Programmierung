package observers;

import domainpackage.Automat;
import observerpattern.Beobachter;


public class AutomatCapacityObserver implements Beobachter {

    private Automat observable;

    public AutomatCapacityObserver(Automat observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }

    @Override
    public void update() {
        int belegtePlaetze = observable.getListCake().size();
        int kapazitaet = observable.getCapacity();
        if (kapazitaet != 0) {
            double auslastung =  belegtePlaetze / (kapazitaet * 1.0);
            if (auslastung > 0.9) {
                System.out.println("Kapazität über 90%");
            }
        }
    }

}
