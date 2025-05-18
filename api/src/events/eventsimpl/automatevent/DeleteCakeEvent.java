package events.eventsimpl.automatevent;

import events.eventsystem.automatsystem.AutomatEvent;

public class DeleteCakeEvent extends AutomatEvent {
    private final int facnummer;

    public DeleteCakeEvent(int facnummer) {
        this.facnummer = facnummer;
    }
    public int getFacnummer() {
        return this.facnummer;
    }
}
