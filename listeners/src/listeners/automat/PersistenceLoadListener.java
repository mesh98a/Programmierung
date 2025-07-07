package listeners.automat;

import domainpackage.Automat;
import domainpackage.dto.AutomatDTO;
import eventsimpl.automatevent.PersistenceLoadEvent;

import eventsystem.automatsystem.AutomatEventListener;
import persistence.AutomatSerializer;
import persistence.AutomatXMLSerializer;

public class PersistenceLoadListener implements AutomatEventListener<PersistenceLoadEvent> {
    private Automat automat;
    //private final CliEventHandler cliEventHandler;

    public PersistenceLoadListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onAutomatEvent(PersistenceLoadEvent event) {
        String mode = event.getMode();
        try {
            if (mode.equalsIgnoreCase("JOS")) {
                Automat loadedAutomat = AutomatSerializer.deserialize("automat.ser");
                this.automat.copyFrom(loadedAutomat);
                if (loadedAutomat != null) {
                    System.err.println("Automat geladet mit JOS");
                }
            } else if (mode.equalsIgnoreCase("JBP")) {
                AutomatDTO loadedAutomat = AutomatXMLSerializer.load("automat.xml");
                this.automat.restoreFromDTO(loadedAutomat);
                if (loadedAutomat != null) {
                    System.err.println("Automat geladet mit JBP");
                }
            } else {
                System.err.println("Unbekannter Modus: " + mode);
            }
        }catch (Exception e) {
            System.err.println("Fehler " + e.getMessage());
        }

    }
}
