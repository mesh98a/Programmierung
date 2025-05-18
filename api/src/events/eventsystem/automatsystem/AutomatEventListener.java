package events.eventsystem.automatsystem;

public interface AutomatEventListener<T extends AutomatEvent>  {
    void onAutomatEvent(T event);
}
