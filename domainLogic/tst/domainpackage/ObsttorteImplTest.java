package domainpackage;

import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Obsttorte;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ObsttorteImplTest {

    @Test
    void getKuchenTyp() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObsttorteImpl kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        KuchenTyp result = kuchen.getKuchenTyp();
        assertEquals(KuchenTyp.OBSTTORTE, result);
    }

    @Test
    void getKremsorte() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObsttorteImpl  kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        String result = kuchen.getKremsorte();
        assertEquals("Honig", result);
    }

    @Test
    void getObstsorte() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObsttorteImpl  kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        String result = kuchen.getObstsorte();
        assertEquals("Apfel", result);
    }

    @Test
    void toDTO() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObsttorteImpl  kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        CakeDTO dto = kuchen.toDTO();
        assertEquals("12.00", dto.getPreis());
    }
}