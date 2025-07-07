package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class PersistenceSaveEvent extends AutomatEvent {
    private final String mode;

    public PersistenceSaveEvent(Object source,String mode) {
        super(source);
        this.mode = mode;
    }
    public String getMode() {
        return this.mode;
    }
}
