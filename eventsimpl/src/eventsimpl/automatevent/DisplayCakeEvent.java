package eventsimpl.automatevent;


import domainpackage.KuchenTyp;
import eventsystem.automatsystem.AutomatEvent;

public class DisplayCakeEvent extends AutomatEvent {
    private final KuchenTyp kuchenTyp;

    public DisplayCakeEvent(KuchenTyp typ) {
        this.kuchenTyp = typ;
    }

    public KuchenTyp getKuchenTyp() {
        return this.kuchenTyp;
    }
}
