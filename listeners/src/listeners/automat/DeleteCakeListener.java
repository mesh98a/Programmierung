package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.DeleteCakeEvent;
import eventsystem.automatsystem.AutomatEventListener;

public class DeleteCakeListener implements AutomatEventListener<DeleteCakeEvent> {
    private final Automat automat;

    public DeleteCakeListener(Automat automat) {
        this.automat = automat;
    }


    @Override
    public void onAutomatEvent(DeleteCakeEvent event) {
        boolean result = this.automat.deleteCake(event.getFacnummer());
        if (result) {
            System.out.println("Kuchen mit " + event.getFacnummer() + " gelöscht");
        }else {
            System.out.println("Kuchen mit " + event.getFacnummer() +  " nicht gelöscht");
        }
    }
}
