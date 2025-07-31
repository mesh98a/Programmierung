package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;

public enum Mode {
    INSERT_CAKE(InsertCakeEvent.class),
    INSERT_HERSTELLER(InsertHerstellerEvent.class),
    DISPLAY_HERSTELLER(GetHerstellerMapEvent.class),
    DISPLAY_CAKE(DisplayCakeEvent.class),
    DISPLAY_ALLERGEN(DisplayAllergenEvent.class),
    DELETE_CAKE(DeleteCakeEvent.class),
    DELETE_HERSTELLER(DeleteHerstellerEvent.class),
    UPDATE_INSPECTDATE(InspectCakeEvent.class),
    PERSIST_SAVE(PersistenceSaveEvent.class),
    PERSIST_LOAD(PersistenceLoadEvent.class);

    private final Class<? extends AutomatEvent> eventClass;

    Mode(Class<? extends AutomatEvent> eventClass) {
        this.eventClass = eventClass;
    }
    public Class<? extends AutomatEvent> getEventClass() {
        return eventClass;
    }
    public static Mode fromEventClassName(String className) {
        for (Mode mode : values()) {
            if (mode.eventClass.getSimpleName().equals(className)) {
                return mode;
            }
        }
        return null;
    }
}
