package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class DisplayKeineAllergenEvent extends AutomatEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DisplayKeineAllergenEvent(Object source) {
        super(source);
    }
}
