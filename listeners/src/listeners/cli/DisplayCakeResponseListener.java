package listeners.cli;

import eventsimpl.clievent.DisplayCakeResponseEvent;
import eventsystem.clisystem.CliEventListener;
import kuchen.Kuchenprodukt;


public class DisplayCakeResponseListener implements CliEventListener<DisplayCakeResponseEvent> {


    @Override
    public void onCliEvent(DisplayCakeResponseEvent event) {
        for( Kuchenprodukt cake : event.getCakeList()){
//
            System.out.print(
                    "Kuchentyp: " + cake.getKuchenTyp() +
                    " Fachnummer: " + cake.getFachnummer() +
                    " Die verbleibende Haltbarkeit : " + cake.getHaltbarkeit().getSeconds()
            );
            if (cake.getInspektionsdatum()!=null){
                System.out.print(" Inspektionsdatum: " + cake.getInspektionsdatum());
            }
            System.out.println();
        }
    }
}
