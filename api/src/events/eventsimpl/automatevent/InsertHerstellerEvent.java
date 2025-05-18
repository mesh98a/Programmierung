package events.eventsimpl.automatevent;

import events.eventsystem.automatsystem.AutomatEvent;

public class InsertHerstellerEvent extends AutomatEvent {
    private final String herstellerName;
    public InsertHerstellerEvent(final String herstellerName) {
        this.herstellerName = herstellerName;
    }

    public String getHerstellerName() {
        return this.herstellerName;
    }
}
