package observers;

import domainpackage.Automat;
import kuchen.Allergen;
import observerpattern.Beobachter;

import java.util.HashSet;
import java.util.Set;


public class AutomatChangesObserver implements Beobachter {
    private Automat observable;
    private Set<Allergen> letzteAllergene = new HashSet<>();

    public AutomatChangesObserver(Automat observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }

    @Override
    public void update() {
        Set<Allergen> aktuelleAllergene = new HashSet<>(observable.getAllergen());

        if (!aktuelleAllergene.equals(letzteAllergene)) {
            System.out.println("Hinweis: Die Allergene haben sich geändert.");
            letzteAllergene = aktuelleAllergene;
        }
    }
}
