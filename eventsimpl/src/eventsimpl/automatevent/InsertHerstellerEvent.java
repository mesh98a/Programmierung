package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class InsertHerstellerEvent extends AutomatEvent {
    private final String herstellerName;

    public InsertHerstellerEvent(Object source, String herstellerName) {
        super(source);
        this.herstellerName = herstellerName;
    }

    public String getHerstellerName() {
        return this.herstellerName;
    }
}
