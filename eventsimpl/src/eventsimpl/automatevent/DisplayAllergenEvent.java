package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class DisplayAllergenEvent extends AutomatEvent {
    private final boolean vorhanden;
    public DisplayAllergenEvent(Object source, boolean vorhanden) {
        super(source);
        this.vorhanden = vorhanden;
    }
    public boolean isVorhanden() {
        return vorhanden;
    }

}
