package eventsimpl.automatevent;


import eventsystem.automatsystem.AutomatEvent;

public class GetHerstellerMapEvent extends AutomatEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GetHerstellerMapEvent(Object source) {
        super(source);
    }
}
