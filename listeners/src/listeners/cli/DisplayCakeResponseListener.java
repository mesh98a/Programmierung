package listeners.cli;

import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.clisystem.CliEventListener;
import kuchen.Kuchenprodukt;

import java.time.Duration;
import java.time.LocalDateTime;

public class DisplayCakeResponseListener implements CliEventListener<DisplayCakeResponseEvent> {


    @Override
    public void onCliEvent(DisplayCakeResponseEvent event) {
        for( Kuchenprodukt cake : event.getCakeList()){
            LocalDateTime ablauf = cake.getEinfuegedatum().plus(cake.getHaltbarkeit());
            LocalDateTime jetzt = LocalDateTime.now();
            System.out.print(
                    "Kuchentyp: " + cake.getKuchenTyp() +
                    " Fachnummer: " + cake.getFachnummer() +
                    " Die verbleibende Haltbarkeit : " + Duration.between(jetzt, ablauf).getSeconds()
            );
            if (cake.getInspektionsdatum()!=null){
                System.out.print(" Inspektionsdatum: " + cake.getInspektionsdatum());
            }
            System.out.println();
        }
    }
}
