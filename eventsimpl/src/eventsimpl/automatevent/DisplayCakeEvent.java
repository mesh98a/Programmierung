package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;
import kuchen.KuchenTyp;

public class DisplayCakeEvent extends AutomatEvent {
    private final String kuchenTyp;

    public DisplayCakeEvent(Object source,String  typ) {
        super(source);
        this.kuchenTyp = typ;

    }

    public String getKuchenTyp() {
        return this.kuchenTyp;
    }
}
