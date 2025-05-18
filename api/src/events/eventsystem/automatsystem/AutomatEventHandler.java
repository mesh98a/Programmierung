package events.eventsystem.automatsystem;

import java.util.LinkedList;
import java.util.List;

public class AutomatEventHandler {
    private final List<AutomatEventListener> listenerList = new LinkedList<>();

    public void add(AutomatEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(AutomatEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(AutomatEvent event){for (AutomatEventListener listener : listenerList) listener.onAutomatEvent(event);}
}
