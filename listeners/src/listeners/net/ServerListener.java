package listeners.net;

import eventsystem.clisystem.CliEvent;
import eventsystem.clisystem.CliEventListener;
import net.Client;
import net.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;

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

