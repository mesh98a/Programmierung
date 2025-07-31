package listeners.net;

import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventListener;
import net.Client;



public class ClientListener implements AutomatEventListener<AutomatEvent> {
    private final Client client;

    public ClientListener(Client client)  {
        this.client = client;
    }

    @Override
    public void onAutomatEvent(AutomatEvent event) {
        if (event != null) client.send(event);
    }
}
