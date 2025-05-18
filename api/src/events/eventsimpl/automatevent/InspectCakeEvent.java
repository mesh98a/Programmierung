package events.eventsimpl.automatevent;

import events.eventsystem.automatsystem.AutomatEvent;

import java.util.Date;

public class InspectCakeEvent extends AutomatEvent {
    private final int fachnummer;
    private final Date Inspektionsdatum;

    public InspectCakeEvent(int fachnummer, Date inspektionsdatum) {
        this.fachnummer = fachnummer;
        Inspektionsdatum = inspektionsdatum;
    }
    public int getFachnummer() {
        return fachnummer;
    }
    public Date getInspektionsdatum() {
        return Inspektionsdatum;
    }
}
