package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.InsertCakeEvent;
import eventsystem.automatsystem.AutomatEventListener;

public class InsertCakeListener implements AutomatEventListener<InsertCakeEvent> {
    private final Automat automat;
    public InsertCakeListener(Automat automat) {
        this.automat = automat;
    }


    @Override
    public void onAutomatEvent(InsertCakeEvent event) {
        boolean result = this.automat.insertCake(event.getCake());
        if (result) {
            System.out.println("Kuchen eingefügt");
        }else {
            System.out.println("Kuchen nicht eingefügt");
        }
    }
}
