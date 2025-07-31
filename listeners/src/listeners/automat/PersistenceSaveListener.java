package listeners.automat;

import domainpackage.Automat;
import domainpackage.dto.AutomatDTO;
import eventsimpl.automatevent.PersistenceSaveEvent;
import eventsystem.automatsystem.AutomatEventListener;
import io.AutomatSerializer;
import io.AutomatXMLSerializer;

public class PersistenceSaveListener implements AutomatEventListener<PersistenceSaveEvent> {
    private final Automat automat;

    public PersistenceSaveListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onAutomatEvent(PersistenceSaveEvent event) {
        String mode = event.getMode();
        try {
            if (mode.equalsIgnoreCase("JOS")) {
                AutomatSerializer.serialize("automat.ser", automat);
                System.err.println("Gespeichert mit JOS.");
            } else if (mode.equalsIgnoreCase("JBP")) {
                AutomatDTO dto = automat.createDTO();

                AutomatXMLSerializer.save("automat.xml", dto);
                System.err.println("Gespeichert mit JBP.");
            } else {
                System.err.println("Unbekannter Modus: " + mode);
            }
        } catch (Exception e) {
            System.err.println("Fehler" + e.getMessage());
        }

    }
}
