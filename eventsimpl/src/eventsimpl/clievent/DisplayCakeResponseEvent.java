package eventsimpl.clievent;

import eventsystem.clisystem.CliEvent;
import kuchen.Kuchenprodukt;

import java.util.List;

public class DisplayCakeResponseEvent extends CliEvent {

    private final List<Kuchenprodukt> cakeList;

    public DisplayCakeResponseEvent(Object source,List<Kuchenprodukt> cakeList) {
        super(source);
        this.cakeList = cakeList;
    }
    public List<Kuchenprodukt> getCakeList() {
        return this.cakeList;
    }
}
