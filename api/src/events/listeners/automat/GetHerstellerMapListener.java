package events.listeners.automat;

import domainpackage.Automat;
import events.eventsimpl.automatevent.GetHerstellerMapEvent;
import events.eventsimpl.clievent.HerstellerMapResponseEvent;
import events.eventsystem.automatsystem.AutomatEventListener;
import events.eventsystem.clisystem.CliEventHandler;
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
