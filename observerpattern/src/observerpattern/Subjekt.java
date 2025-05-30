package observerpattern;

public interface Subjekt {
    void registerObserver(Beobachter o);
    void removeObserver(Beobachter o);
    void notifyObservers();
}
