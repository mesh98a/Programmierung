package events.listeners.automat;

import domainpackage.Automat;
import events.eventsimpl.automatevent.InsertHerstellerEvent;
import events.eventsystem.automatsystem.AutomatEventListener;

public class InsertHerstellerListener implements AutomatEventListener<InsertHerstellerEvent> {
    private final Automat automat;

    public InsertHerstellerListener(Automat automat) {
        this.automat = automat;
    }
    @Override
    public void onAutomatEvent(InsertHerstellerEvent event) {
//        System.out.println("Hersteller eingefügt");
        this.automat.insertHersteller(event.getHerstellerName());
    }
}
