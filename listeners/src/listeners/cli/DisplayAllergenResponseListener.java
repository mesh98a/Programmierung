package listeners.cli;

import eventsimpl.clievent.DisplayAllergenResponseEvent;
import eventsystem.clisystem.CliEventListener;
import kuchen.Allergen;

public class DisplayAllergenResponseListener implements CliEventListener<DisplayAllergenResponseEvent> {

    @Override
    public void onCliEvent(DisplayAllergenResponseEvent event) {
        System.out.println("Allergene:");
        for (Allergen allergen : event.getAllergen()){
            System.out.println(" - " + allergen);
        }
    }
}
