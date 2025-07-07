package listeners.automat;

import domainpackage.Automat;
import kuchen.KuchenTyp;
import eventsimpl.automatevent.DisplayCakeEvent;
import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.automatsystem.AutomatEventListener;
import eventsystem.clisystem.CliEventHandler;
import kuchen.Kuchenprodukt;

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
        List<Kuchenprodukt> cakeList;
        KuchenTyp typ = event.getKuchenTyp();

        if (typ == null) {
            cakeList = this.automat.getListCake();
            cliEventHandler.handle(new DisplayCakeResponseEvent(automat,cakeList));
        } else {
            cakeList = this.automat.getListCake(typ);
            cliEventHandler.handle(new DisplayCakeResponseEvent(automat,cakeList));
        }
    }
}
