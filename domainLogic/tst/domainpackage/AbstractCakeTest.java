package domainpackage;

import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCakeTest {
    public Hersteller hersteller;
    public ObstkuchenImpl kuchen;
    @BeforeEach
    void setUp() {
        this.hersteller = new HerstellerImpl("Sonne");
        this.kuchen = new ObstkuchenImpl(hersteller, new BigDecimal("10.00"), 350, Duration.ofDays(5), Set.of(Allergen.Gluten), "Apfel");
    }
    @Test
    void getKuchenTyp() {
        KuchenTyp result = kuchen.getKuchenTyp();
        assertEquals(KuchenTyp.OBSTKUCHEN, result);
    }

    @Test
    void getHersteller() {
        Hersteller result = kuchen.getHersteller();
        assertEquals(hersteller, result);
    }

    @Test
    void getAllergene() {
        Collection result = kuchen.getAllergene();
        assertEquals(Set.of(Allergen.Gluten), result);
    }

    @Test
    void getNaehrwert() {
        int result = kuchen.getNaehrwert();
        assertEquals(350, result);
    }

    @Test
    void getHaltbarkeit() {
        Duration result = kuchen.getHaltbarkeit();
        assertNotNull(result);
    }

    @Test
    void getPreis() {
        BigDecimal result = kuchen.getPreis();
        assertEquals(new BigDecimal("10.00"), result);
    }

    @Test
    void getInspektionsdatum() {
        kuchen.setInspektionsdatum(new Date());
        Date result = kuchen.getInspektionsdatum();
        assertNotNull(result);
    }

    @Test
    void getFachnummer() {
        kuchen.setFachnummer(2);
        int result = kuchen.getFachnummer();
        assertEquals(2, result);
    }

    @Test
    void setInspektionsdatum() {
        kuchen.setInspektionsdatum(new Date());
        Date result = kuchen.getInspektionsdatum();
        assertNotNull(result);
    }

    @Test
    void setFachnummer() {
        kuchen.setFachnummer(2);
        int result = kuchen.getFachnummer();
        assertEquals(2, result);
    }

    @Test
    void setEinfuegedatum() {
        kuchen.setEinfuegedatum(LocalDateTime.now());
        LocalDateTime result = kuchen.getEinfuegedatum();
        assertNotNull(result);
    }


    @Test
    void getEinfuegedatum() {
        kuchen.setEinfuegedatum(LocalDateTime.now());
        LocalDateTime result = kuchen.getEinfuegedatum();
        assertNotNull(result);
    }

    @Test
    void toDTO() {
        CakeDTO dto = kuchen.toDTO();
        assertEquals("10.00", dto.getPreis());
    }
    @Test
    void toDTO_Inspektionsdatum(){
        kuchen.setInspektionsdatum(new Date());
        CakeDTO dto = kuchen.toDTO();
        assertNotNull(dto.getInspektionsdatum());
    }
    @Test
    void  toDTO_Einfuegedatum(){
        kuchen.setEinfuegedatum(LocalDateTime.now());
        CakeDTO dto = kuchen.toDTO();
        assertNotNull(dto.getEinfuegedatum());
    }
}