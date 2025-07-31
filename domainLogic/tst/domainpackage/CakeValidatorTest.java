package domainpackage;

import kuchen.Allergen;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.time.Duration;
import java.util.*;
import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

class CakeValidatorTest {

    @Test
    void validate_Hersteller_Null() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(null, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.NULL_VALUE,result);
    }
    @Test
    void validate_Hersteller_No_Name() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.NO_NAME,result);
    }
    @Test
    void validate_Naehrwert_Too_Small() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 0, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.TOO_SMALL,result);
    }
    @Test
    void validate_Haltbarkeit_Null() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, null, Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.NULL_VALUE,result);
    }
    @Test
    void validate_Haltbarkeit_Too_Small() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(-5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.TOO_SMALL,result);
    }
    @Test
    void validate_Preis_Null() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, null, 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.NULL_VALUE,result);
    }
    @Test
    void validate_Preis_Too_Small() {
        CakeValidator cakeValidator = new CakeValidator();
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("-12.00"),350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Apfel", "Honig");
        ErrorCake result = cakeValidator.validate(kuchen);
        assertEquals(ErrorCake.TOO_SMALL,result);
    }

}