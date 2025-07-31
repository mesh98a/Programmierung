package domainpackage;

import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ObstkuchenImplTest {

    @Test
    void getObstsorte() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObstkuchenImpl kuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel");
        String result = kuchen.getObstsorte();
        assertEquals("Apfel", result);
    }

    @Test
    void getKuchenTyp() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObstkuchenImpl kuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel");
        KuchenTyp result = kuchen.getKuchenTyp();
        assertEquals(KuchenTyp.OBSTKUCHEN, result);
    }

    @Test
    void toDTO() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        ObstkuchenImpl kuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel");
        CakeDTO dto = kuchen.toDTO();
        assertEquals("12.00", dto.getPreis());
    }
}