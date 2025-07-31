package commands;

import eventsimpl.automatevent.InsertHerstellerEvent;
import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ReadCommandTest {

    @Test
    void readHersteller() {
        AutomatEventHandler handler = mock(AutomatEventHandler.class);
        Map<Mode, AutomatEventHandler> handlers = new HashMap<>();
        handlers.put(Mode.DISPLAY_HERSTELLER, handler);

        ReadCommand command = new ReadCommand();

        command.readHersteller(handlers);
        ArgumentCaptor<InsertHerstellerEvent> captor = ArgumentCaptor.forClass(InsertHerstellerEvent.class);
        verify(handler).handle(captor.capture());
        InsertHerstellerEvent event = captor.getValue();
        assertEquals("Hersteller", event.getHerstellerName());
    }

}