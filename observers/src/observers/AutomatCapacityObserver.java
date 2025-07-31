package observers;

import domainpackage.Automat;
import net.Server;
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
        double auslastung = (double) belegtePlaetze / kapazitaet;
        if (auslastung > 0.9) {
            System.out.println("Kapazität über 90%");
        }
    }

}
