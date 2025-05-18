package events.eventsimpl.automatevent;

import domainpackage.AbstractCake;
import events.eventsystem.automatsystem.AutomatEvent;

public class InsertCakeEvent extends AutomatEvent {
    private final AbstractCake cake;

    public InsertCakeEvent(final AbstractCake cake) {
        this.cake = cake;
    }

    public AbstractCake getCake() {
        return this.cake;
    }

}
