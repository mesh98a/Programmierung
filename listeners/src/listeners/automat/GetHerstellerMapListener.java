package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.GetHerstellerMapEvent;
import eventsimpl.clievent.HerstellerMapResponseEvent;
import eventsystem.clisystem.CliEventHandler;
import eventsystem.automatsystem.AutomatEventListener;
import verwaltung.Hersteller;

import java.util.Map;

public class GetHerstellerMapListener implements AutomatEventListener<GetHerstellerMapEvent> {
    private final Automat automat;
    private final CliEventHandler cliEventHandler;

    public GetHerstellerMapListener(Automat automat, CliEventHandler cliEventHandler) {
        this.automat = automat;
        this.cliEventHandler = cliEventHandler;
    }

    @Override
    public void onAutomatEvent(GetHerstellerMapEvent event) {
        Map<Hersteller, Integer> map = this.automat.getHerstellerMap();
        cliEventHandler.handle(new HerstellerMapResponseEvent(map));
    }
}
