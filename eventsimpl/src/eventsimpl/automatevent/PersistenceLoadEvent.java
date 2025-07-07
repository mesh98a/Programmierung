package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class PersistenceLoadEvent extends AutomatEvent {
    private final String mode;

    public PersistenceLoadEvent(Object source,String mode) {
        super(source);
        this.mode = mode;
    }
    public String getMode() {
        return this.mode;
    }
}
