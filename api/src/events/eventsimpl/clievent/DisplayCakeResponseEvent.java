package events.eventsimpl.clievent;

import domainpackage.AbstractCake;
import events.eventsystem.clisystem.CliEvent;

import java.util.List;

public class DisplayCakeResponseEvent extends CliEvent {

    private final List<AbstractCake> cakeList;

    public DisplayCakeResponseEvent(List<AbstractCake> cakeList) {
        this.cakeList = cakeList;
    }
    public List<AbstractCake> getCakeList() {
        return this.cakeList;
    }
}
