package listeners.cli;

import eventsimpl.automatevent.DisplayAllergenEvent;
import eventsimpl.clievent.DisplayAllergenResponseEvent;
import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.clisystem.CliEventListener;
import kuchen.Allergen;

public class DisplayAllergenResponseListener implements CliEventListener<DisplayAllergenResponseEvent> {

    @Override
    public void onCliEvent(DisplayAllergenResponseEvent event) {
        System.out.println("Allergene [enthalten (i)]:");
        for (Allergen allergen : event.getAllergen()){
            System.out.println(" - " + allergen);
        }
    }
}
