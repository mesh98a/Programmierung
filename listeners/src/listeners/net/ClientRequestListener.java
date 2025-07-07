package listeners.net;

import eventsystem.automatsystem.*;
import net.Client;

public class ClientRequestListener implements AutomatEventListener<AutomatEvent> {
    private final Client client;

    public ClientRequestListener(Client client) {
        this.client = client;
    }

    @Override
    public void onAutomatEvent(AutomatEvent event) {
        if (event != null) {
            client.send(event);
            client.receive();
        }
    }
}
