package listeners.automat;

import domainpackage.Automat;
import eventsimpl.automatevent.InsertHerstellerEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;


class InsertHerstellerListenerTest {

    @Test
    void properOutput() {
        PrintStream originalOut = System.err;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setErr(out);
            InsertHerstellerEvent event = mock(InsertHerstellerEvent.class);
            when(event.getHerstellerName()).thenReturn("Hersteller");
            Automat automat = new Automat(3);
            InsertHerstellerListener listener = new InsertHerstellerListener(automat);

            listener.onAutomatEvent(event);

            verify(out).println("Hersteller eingefügt");
        } finally {
            System.setErr(originalOut);
        }
    }

    @Test
    void properOutputWrong() {
        PrintStream originalOut = System.err;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setErr(out);
            InsertHerstellerEvent event = mock(InsertHerstellerEvent.class);
            when(event.getHerstellerName()).thenReturn(null);
            Automat automat = new Automat(1);
            InsertHerstellerListener listener = new InsertHerstellerListener(automat);

            listener.onAutomatEvent(event);

            verify(out).println("Hersteller nicht eingefügt");
        } finally {
            System.setErr(originalOut);
        }
    }

}