package domainpackage;

import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerImplTest {

    @Test
    void getName() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        assertEquals("Sonne", hersteller.getName());
    }

    @Test
    void testEquals() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        Hersteller other = new HerstellerImpl("Sonne");
        assertEquals(hersteller,other);
    }

    @Test
    void testHashCode() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        Hersteller other = new HerstellerImpl("Sonne");
        assertEquals(hersteller.hashCode(),other.hashCode());
    }
}