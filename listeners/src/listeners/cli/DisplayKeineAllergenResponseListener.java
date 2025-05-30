package listeners.cli;

import eventsimpl.clievent.DisplayAllergenResponseEvent;
import eventsimpl.clievent.DisplayKeineAllergenResponseEvent;
import eventsystem.clisystem.CliEventListener;
import kuchen.Allergen;

public class DisplayKeineAllergenResponseListener  implements CliEventListener<DisplayKeineAllergenResponseEvent> {

    @Override
    public void onCliEvent(DisplayKeineAllergenResponseEvent event) {
        System.out.println("Allergene [nicht enthalten (e)]:");
        for (Allergen allergen : event.getAllergen()){
            System.out.println(" - " + allergen);
        }
    }
}
