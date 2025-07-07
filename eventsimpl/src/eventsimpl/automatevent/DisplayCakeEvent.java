package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;
import kuchen.KuchenTyp;

public class DisplayCakeEvent extends AutomatEvent {
    private final KuchenTyp kuchenTyp;

    public DisplayCakeEvent(Object source,KuchenTyp  typ) {
        super(source);
        this.kuchenTyp = typ;

    }

    public KuchenTyp getKuchenTyp() {
        return this.kuchenTyp;
    }
}
