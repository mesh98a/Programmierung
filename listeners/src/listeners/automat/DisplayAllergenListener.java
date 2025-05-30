package listeners.automat;


import domainpackage.Automat;
import eventsimpl.automatevent.DisplayAllergenEvent;
import eventsimpl.clievent.DisplayAllergenResponseEvent;
import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.automatsystem.AutomatEventListener;
import eventsystem.clisystem.CliEventHandler;
import kuchen.Allergen;

import java.util.Set;

public class DisplayAllergenListener implements AutomatEventListener<DisplayAllergenEvent> {
    private final Automat automat;
    private final CliEventHandler cliEventHandler;

    public DisplayAllergenListener(Automat automat, CliEventHandler cliEventHandler) {
        this.automat = automat;
        this.cliEventHandler = cliEventHandler;
    }

    @Override
    public void onAutomatEvent(DisplayAllergenEvent event) {
        Set<Allergen> allergenSet = this.automat.displayAllergen();
        cliEventHandler.handle(new DisplayAllergenResponseEvent(allergenSet));

    }
}
