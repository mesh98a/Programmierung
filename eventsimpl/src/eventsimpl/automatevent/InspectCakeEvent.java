package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class InspectCakeEvent extends AutomatEvent {
    private final int fachnummer;

    public InspectCakeEvent(Object source,int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }

    public int getFachnummer() {
        return fachnummer;
    }

}
