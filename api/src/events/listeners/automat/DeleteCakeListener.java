package events.listeners.automat;

import domainpackage.Automat;
import events.eventsimpl.automatevent.DeleteCakeEvent;
import events.eventsystem.automatsystem.AutomatEventListener;

public class DeleteCakeListener implements AutomatEventListener<DeleteCakeEvent> {
    private final Automat automat;

    public DeleteCakeListener(Automat automat) {
        this.automat = automat;
    }


    @Override
    public void onAutomatEvent(DeleteCakeEvent event) {
        boolean result = this.automat.deleteCake(event.getFacnummer());
//        if (result) {
//            System.out.println("Kuchen gelöscht");
//        }else {
//            System.out.println("Kuchen nicht gelöscht");
//        }
    }
}
