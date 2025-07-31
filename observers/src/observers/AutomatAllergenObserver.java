package observers;

import domainpackage.Automat;
import kuchen.Allergen;
import observerpattern.Beobachter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class AutomatAllergenObserver implements Beobachter{
    private static final long serialVersionUID = 1L;
    private Automat observable;
    private Set<Allergen> letzteAllergene = new HashSet<>();

    public AutomatAllergenObserver(Automat observable) {
        this.observable = observable;
        observable.registerObserver(this);
    }

    @Override
    public void update() {
        Set<Allergen> aktuelleAllergene = new HashSet<>(observable.getAllergen(true));

        if (!aktuelleAllergene.equals(letzteAllergene)) {
            System.out.println("Hinweis: Die Allergene haben sich geändert.");
            letzteAllergene = aktuelleAllergene;
        }
    }
}
