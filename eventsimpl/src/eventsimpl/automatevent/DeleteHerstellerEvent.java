package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class DeleteHerstellerEvent extends AutomatEvent {
    private final String herstellerName;

    public DeleteHerstellerEvent(String herstellerName) {
        this.herstellerName = herstellerName;
    }
    public String getHerstellerName() {
        return this.herstellerName;
    }
}
