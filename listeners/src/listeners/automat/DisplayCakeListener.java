package listeners.automat;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import eventsimpl.automatevent.DisplayCakeEvent;
import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.automatsystem.AutomatEventListener;
import eventsystem.clisystem.CliEventHandler;

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
        List<AbstractCake> cakeList;
        if (event.getKuchenTyp() == null) {
            cakeList = this.automat.displayListCake();
            cliEventHandler.handle(new DisplayCakeResponseEvent(cakeList));
        } else {
            cakeList = this.automat.displayListCake(event.getKuchenTyp());
            cliEventHandler.handle(new DisplayCakeResponseEvent(cakeList));
        }
    }
}
