package observerpattern.observerimpl;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import observerpattern.observer.Observable;
import observerpattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableAutomat extends Automat implements Observable {
    private List<Observer> observers;

    public ObservableAutomat(int capacity) {
        super(capacity);
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    @Override
    public boolean insertCake(AbstractCake cake) {
        boolean inserted = super.insertCake(cake);
        if (inserted){
            notifyObservers();
        }
        return inserted;
    }




}
