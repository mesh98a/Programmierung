package events.listeners.cli;

import events.eventsimpl.clievent.HerstellerMapResponseEvent;
import events.eventsystem.clisystem.CliEventListener;
import verwaltung.Hersteller;

public class HerstellerMapResponseListener implements CliEventListener<HerstellerMapResponseEvent> {


    @Override
    public void onCliEvent(HerstellerMapResponseEvent event) {
        for (Hersteller h : event.getHerstellerMap().keySet()) {
            System.out.println("Hersteller*innen: " + h.getName() + " Anzahl: " + event.getHerstellerMap().get(h));
        }
    }
}
