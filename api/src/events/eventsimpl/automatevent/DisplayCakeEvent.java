package events.eventsimpl.automatevent;

import domainpackage.KuchenTyp;
import events.eventsystem.automatsystem.AutomatEvent;

public class DisplayCakeEvent extends AutomatEvent {
    private final KuchenTyp kuchenTyp;

    public DisplayCakeEvent(KuchenTyp typ) {
        kuchenTyp = typ;
    }
    public KuchenTyp getKuchenTyp() {
        return this.kuchenTyp;
    }
}
