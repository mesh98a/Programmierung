package events.eventsimpl.clievent;

import events.eventsystem.clisystem.CliEvent;
import verwaltung.Hersteller;

import java.util.Map;

public class HerstellerMapResponseEvent extends CliEvent {

    private final Map<Hersteller, Integer> map;

    public HerstellerMapResponseEvent(Map<Hersteller, Integer> map) {
        this.map = map;
    }

    public Map<Hersteller, Integer> getHerstellerMap() {
        return this.map;
    }
}
