package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.InsertHerstellerEvent;
import eventsystem.automatsystem.AutomatEventListener;

public class InsertHerstellerListener implements AutomatEventListener<InsertHerstellerEvent> {
    private final Automat automat;

    public InsertHerstellerListener(Automat automat) {
        this.automat = automat;
    }
    @Override
    public void onAutomatEvent(InsertHerstellerEvent event) {
        boolean result = this.automat.insertHersteller(event.getHerstellerName());
        if(result){
            System.out.println("Hersteller eingefügt");
        }else {
            System.out.println("Hersteller nicht gefunden");
        }
    }
}
