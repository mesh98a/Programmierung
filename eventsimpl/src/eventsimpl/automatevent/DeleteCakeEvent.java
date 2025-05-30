package eventsimpl.automatevent;
import eventsystem.automatsystem.AutomatEvent;

public class DeleteCakeEvent extends AutomatEvent {
    private final int fachnummer;

    public DeleteCakeEvent(int facnummer) {
        this.fachnummer = facnummer;
    }
    public int getFacnummer() {
        return this.fachnummer;
    }
}
