package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public class DisplayCakeEvent extends AutomatEvent {
    private final String kuchenTyp;

    public DisplayCakeEvent(String  typ) {
        this.kuchenTyp = typ;
    }

    public String getKuchenTyp() {
        return this.kuchenTyp;
    }
}
