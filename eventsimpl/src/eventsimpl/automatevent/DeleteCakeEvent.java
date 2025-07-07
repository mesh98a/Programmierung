package eventsimpl.automatevent;
import eventsystem.automatsystem.AutomatEvent;

public class DeleteCakeEvent extends AutomatEvent {
    private final int fachnummer;


    public DeleteCakeEvent(Object source,int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }
    public int getFacnummer() {
        return this.fachnummer;
    }
}
