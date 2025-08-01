package listeners.net;

import eventsystem.clisystem.CliEvent;
import eventsystem.clisystem.CliEventListener;
import net.Server;



public class ServerListener implements CliEventListener<CliEvent> {

    private final Server server;

    public ServerListener(Server server) {
        this.server = server;
    }

    @Override
    public void onCliEvent(CliEvent event) {
        this.server.send(event);
    }
}

