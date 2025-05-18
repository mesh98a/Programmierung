package events.listeners.cli;

import domainpackage.AbstractCake;
import events.eventsimpl.clievent.DisplayCakeResponseEvent;
import events.eventsystem.clisystem.CliEventListener;

import java.time.Duration;
import java.time.LocalDateTime;

public class DisplayCakeResponseListener implements CliEventListener<DisplayCakeResponseEvent> {


    @Override
    public void onCliEvent(DisplayCakeResponseEvent event) {
        for( AbstractCake cake : event.getCakeList()){
            LocalDateTime ablauf = cake.getEinfuegedatum().plus(cake.getHaltbarkeit());
            LocalDateTime jetzt = LocalDateTime.now();
            System.out.print(
                    "Fachnummer: " + cake.getFachnummer() +
                    " Die verbleibende Haltbarkeit : " + Duration.between(jetzt, ablauf).getSeconds()
            );
            if (cake.getInspektionsdatum()!=null){
                System.out.print(" Inspektionsdatum: " + cake.getInspektionsdatum());
            }
            System.out.println();
        }
    }
}
