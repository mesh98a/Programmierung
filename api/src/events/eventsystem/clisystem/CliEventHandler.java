package events.eventsystem.clisystem;

import java.util.LinkedList;
import java.util.List;

public class CliEventHandler {

    private final List<CliEventListener> listenerList = new LinkedList<>();

    public void add(CliEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(CliEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(CliEvent event){for (CliEventListener listener : listenerList) listener.onCliEvent(event);}
}
