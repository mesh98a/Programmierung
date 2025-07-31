package domainpackage;

import kuchen.Allergen;
import kuchen.KuchenTyp;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CakeFactoryTest {

    @Test
    void createObstkuchen() {
        KuchenTyp typ = KuchenTyp.OBSTKUCHEN;
        Hersteller hersteller = new HerstellerImpl("Sonne");
        BigDecimal preis = new BigDecimal("15.00");
        int naehrwert = 200;
        Duration haltbarkeit = Duration.ofDays(3);
        Collection allergene = Set.of(Allergen.Haselnuss);
        List<String> kuchensorten = List.of("Apfel");
        AbstractCake result = CakeFactory.createCake(typ,hersteller,preis,naehrwert,haltbarkeit,allergene,kuchensorten);
        assertEquals(200,result.getNaehrwert());
    }
    @Test
    void createKremkuchen() {
        KuchenTyp typ = KuchenTyp.KREMKUCHEN;
        Hersteller hersteller = new HerstellerImpl("Sonne");
        BigDecimal preis = new BigDecimal("15.00");
        int naehrwert = 200;
        Duration haltbarkeit = Duration.ofDays(3);
        Collection allergene = Set.of(Allergen.Haselnuss);
        List<String> kuchensorten = List.of("Honig");
        AbstractCake result = CakeFactory.createCake(typ,hersteller,preis,naehrwert,haltbarkeit,allergene,kuchensorten);
        assertEquals(200,result.getNaehrwert());
    }
    @Test
    void createObsttorte() {
        KuchenTyp typ = KuchenTyp.OBSTTORTE;
        Hersteller hersteller = new HerstellerImpl("Sonne");
        BigDecimal preis = new BigDecimal("15.00");
        int naehrwert = 200;
        Duration haltbarkeit = Duration.ofDays(3);
        Collection allergene = Set.of(Allergen.Haselnuss);
        List<String> kuchensorten = List.of("Apfel","Honig");
        AbstractCake result = CakeFactory.createCake(typ,hersteller,preis,naehrwert,haltbarkeit,allergene,kuchensorten);
        assertEquals(200,result.getNaehrwert());
    }
}