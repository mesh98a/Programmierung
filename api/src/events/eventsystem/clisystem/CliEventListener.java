package events.eventsystem.clisystem;

public interface CliEventListener<T extends CliEvent>{
    void onCliEvent(T event);
}
