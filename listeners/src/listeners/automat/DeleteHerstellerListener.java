package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.DeleteHerstellerEvent;
import eventsystem.automatsystem.AutomatEventListener;

import java.awt.event.ActionListener;

public class DeleteHerstellerListener implements AutomatEventListener<DeleteHerstellerEvent> {
    private final Automat automat;

    public DeleteHerstellerListener(Automat automat) {
        this.automat = automat;
    }


    @Override
    public void onAutomatEvent(DeleteHerstellerEvent event) {
        boolean result = this.automat.deleteHersteller(event.getHerstellerName());
        if (result) {
            System.err.println("Hersteller " + event.getHerstellerName() + " gelöscht.");
        } else {
            System.err.println("Hersteller " + event.getHerstellerName() + " nicht gelöscht.");
        }
    }
}
