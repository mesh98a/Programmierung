package events.listeners.automat;


import domainpackage.Automat;
import events.eventsimpl.automatevent.InspectCakeEvent;
import events.eventsystem.automatsystem.AutomatEventListener;

public class InspectCakeListener implements AutomatEventListener<InspectCakeEvent> {
    private final Automat automat;

    public InspectCakeListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onAutomatEvent(InspectCakeEvent event) {
        boolean result = this.automat.inspectCake(event.getFachnummer(),event.getInspektionsdatum());
//        if (result) {
//            System.out.println("Inspektionsdatum eingesetzt");
//        }else {
//            System.out.println("Inspektionsdatum nicht eingesetzt");
//        }
    }
}
