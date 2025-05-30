package eventsimpl.clievent;

import eventsystem.clisystem.CliEvent;
import kuchen.Allergen;

import java.util.Set;

public class DisplayAllergenResponseEvent extends CliEvent {
    private final Set<Allergen> allergenSet;

    public DisplayAllergenResponseEvent(Set<Allergen> allergenSet) {
        this.allergenSet = allergenSet;
    }

    public Set<Allergen> getAllergen() {
        return this.allergenSet;
    }
}
