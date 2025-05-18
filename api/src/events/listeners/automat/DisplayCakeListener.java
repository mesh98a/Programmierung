package events.listeners.automat;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import events.eventsimpl.automatevent.DisplayCakeEvent;
import events.eventsimpl.clievent.DisplayCakeResponseEvent;
import events.eventsystem.clisystem.CliEventHandler;
import events.eventsystem.automatsystem.AutomatEventListener;

import java.util.List;

public class DisplayCakeListener implements AutomatEventListener<DisplayCakeEvent> {
    private final Automat automat;
    private final CliEventHandler cliEventHandler;

    public DisplayCakeListener(Automat automat, CliEventHandler cliEventHandler) {
        this.automat = automat;
        this.cliEventHandler = cliEventHandler;
    }

    @Override
    public void onAutomatEvent(DisplayCakeEvent event) {
        List<AbstractCake> cakeList = this.automat.displayListCake(event.getKuchenTyp());
        cliEventHandler.handle(new DisplayCakeResponseEvent(cakeList));
    }
}
