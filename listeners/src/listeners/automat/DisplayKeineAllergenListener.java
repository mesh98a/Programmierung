package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.DisplayAllergenEvent;
import eventsimpl.automatevent.DisplayKeineAllergenEvent;
import eventsimpl.clievent.DisplayAllergenResponseEvent;
import eventsimpl.clievent.DisplayKeineAllergenResponseEvent;
import eventsystem.automatsystem.AutomatEventListener;
import eventsystem.clisystem.CliEventHandler;
import kuchen.Allergen;

import java.util.Set;

public class DisplayKeineAllergenListener implements AutomatEventListener<DisplayKeineAllergenEvent> {

    private final Automat automat;
    private final CliEventHandler cliEventHandler;

    public DisplayKeineAllergenListener(Automat automat, CliEventHandler cliEventHandler) {
        this.automat = automat;
        this.cliEventHandler = cliEventHandler;
    }

    @Override
    public void onAutomatEvent(DisplayKeineAllergenEvent event) {
        Set<Allergen> allergenSet = this.automat.displaykeineAllergen();
        cliEventHandler.handle(new DisplayKeineAllergenResponseEvent(automat,allergenSet));
    }
}
