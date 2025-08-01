package eventsimpl.automatevent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertHerstellerEventTest {

    @Test
    void getHerstellerName() {

        InsertHerstellerEvent event = new InsertHerstellerEvent(this,"Hersteller");

        assertEquals("Hersteller",event.getHerstellerName());
    }
}