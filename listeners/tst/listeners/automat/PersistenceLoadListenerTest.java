package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.PersistenceLoadEvent;
import eventsystem.automatsystem.AutomatEvent;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.Console;
import java.io.ObjectInput;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceLoadListenerTest {

    @Test
    void onAutomatEvent() {

        PersistenceLoadListener listener = mock((PersistenceLoadListener.class));
        PersistenceLoadEvent event = mock(PersistenceLoadEvent.class);
        when(event.getMode()).thenReturn("JOS");
        verify(listener).onAutomatEvent(event);
    }
}