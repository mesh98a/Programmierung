package eventsimpl.clievent;

import eventsystem.clisystem.CliEvent;
import kuchen.Allergen;

import java.util.Set;

public class DisplayKeineAllergenResponseEvent extends CliEvent {
    private final Set<Allergen> allergenSet;

    public DisplayKeineAllergenResponseEvent(Set<Allergen> allergenSet) {
        this.allergenSet = allergenSet;
    }

    public Set<Allergen> getAllergen() {
        return this.allergenSet;
    }
}
