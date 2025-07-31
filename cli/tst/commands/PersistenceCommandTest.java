package commands;

import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.PersistenceLoadEvent;
import eventsimpl.automatevent.PersistenceSaveEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import parser.PersistenceParser;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceCommandTest {

    @Test
    void saveWithJOS() {
        PersistenceParser parser = mock(PersistenceParser.class);
        AutomatEventHandler handler = mock(AutomatEventHandler.class);
        when(parser.getMode()).thenReturn("JOS");
        Map<Mode, AutomatEventHandler> handlers = new HashMap<>();
        handlers.put(Mode.PERSIST_SAVE, handler);
        PersistenceCommand command = new PersistenceCommand();
        command.save(parser, handlers);
        ArgumentCaptor<PersistenceSaveEvent> captor = ArgumentCaptor.forClass(PersistenceSaveEvent.class);
        verify(handler).handle(captor.capture());
        PersistenceSaveEvent event = captor.getValue();
        assertEquals("JOS", event.getMode());
    }

    @Test
    void loadWithJOS() {
        PersistenceParser parser = mock(PersistenceParser.class);
        AutomatEventHandler handler = mock(AutomatEventHandler.class);
        when(parser.getMode()).thenReturn("JOS");
        Map<Mode, AutomatEventHandler> handlers = new HashMap<>();
        handlers.put(Mode.PERSIST_LOAD, handler);
        PersistenceCommand command = new PersistenceCommand();
        command.load(parser, handlers);
        ArgumentCaptor<PersistenceLoadEvent> captor = ArgumentCaptor.forClass(PersistenceLoadEvent.class);
        verify(handler).handle(captor.capture());
        PersistenceLoadEvent event = captor.getValue();
        assertEquals("JOS", event.getMode());
    }
}