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

class KremkuchenImplTest {

    @Test
    void getKremsorte() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        KremkuchenImpl kuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Honig");
        String result = kuchen.getKremsorte();
        assertEquals("Honig", result);
    }

    @Test
    void getKuchenTyp() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        KremkuchenImpl kuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Honig");
        KuchenTyp result = kuchen.getKuchenTyp();
        assertEquals(KuchenTyp.KREMKUCHEN, result);
    }

    @Test
    void toDTO() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        KremkuchenImpl kuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Honig");
        CakeDTO dto = kuchen.toDTO();
        assertEquals("12.00", dto.getPreis());
    }
}