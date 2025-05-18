package observerpattern.observerimpl;

import observerpattern.observer.Observer;

public class AutomatObserver implements Observer {
    private ObservableAutomat observable;
    public AutomatObserver(ObservableAutomat observable) {
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
