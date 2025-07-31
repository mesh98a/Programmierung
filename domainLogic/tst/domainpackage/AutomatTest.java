package domainpackage;

import domainpackage.dto.AutomatDTO;
import kuchen.*;
import observerpattern.Beobachter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutomatTest {


    @Test
    void insertCake() {
        //setup
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake kuchen = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel", "Honig");
        // Methode unter Test

        boolean result = aut.insertCake(kuchen);
        // zusicherung
        assertTrue(result);
    }

    @Test
    void insertCake_Null() {
        Automat aut = new Automat(1);
        aut.insertHersteller("Sonne");

        boolean result = aut.insertCake(null);
        assertFalse(result);
    }

    @Test
    void insertCake_Null_Hersteller() {
        Automat aut = new Automat(10);
        AbstractCake apfelkuchen = new ObstkuchenImpl(null, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        boolean result = aut.insertCake(apfelkuchen);

        assertFalse(result);
    }

    @Test
    void insertCake_Hersteller_Not_In_Map() {
        Automat aut = new Automat(10);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        boolean result = aut.insertCake(apfelkuchen);

        assertFalse(result);
    }

    @Test
    void insertCake_Automat_Is_Full() {
        Automat aut = new Automat(0);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        //
        boolean result = aut.insertCake(apfelkuchen);
        //
        assertFalse(result);
    }

    @Test
    void insertCake_Naehrwert_TooSmall() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Set.of(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(-100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }

    @Test
    void insertCake_Haltbarkeit_Null() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Set.of(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(null);
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void insert_Haltbarkeit_TooSmall() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getNaehrwert()).thenReturn(100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(-1));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void insertCake_Preis_Null() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getNaehrwert()).thenReturn(-100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(null);
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void insertCake_Preis_TooSmall() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Set.of(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(-100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }


    @Test
    void getListCake() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        //
        List<Kuchenprodukt> kuchenListe = aut.getListCake();
        //
        assertEquals(1, kuchenListe.size());
    }

    @Test
    void filterCake() {
        Automat aut = new Automat(4);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("10.00"), 350, Duration.ofDays(5), Set.of(Allergen.Erdnuss), "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        KuchenTyp typ = KuchenTyp.OBSTKUCHEN;

        List<Kuchenprodukt> kuchenListe = aut.getListCake(typ);

        assertEquals(2, kuchenListe.size());
    }

    @Test
    void getListCake_With_Two_Different_Cakes() {
        Automat aut = new Automat(2);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = mock(ObstkuchenImpl.class);
        when(apfelkuchen.getHersteller()).thenReturn(hersteller);
        when(apfelkuchen.getNaehrwert()).thenReturn(100);
        when(apfelkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(4));
        when(apfelkuchen.getPreis()).thenReturn(new BigDecimal("12.00"));
        aut.insertCake(apfelkuchen);
        AbstractCake schokokuchen = mock(KremkuchenImpl.class);
        when(schokokuchen.getHersteller()).thenReturn(hersteller);
        when(schokokuchen.getNaehrwert()).thenReturn(100);
        when(schokokuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(schokokuchen.getPreis()).thenReturn(new BigDecimal("10.00"));

        aut.insertCake(schokokuchen);
        //
        List<Kuchenprodukt> cakes = aut.getListCake();
        //
        assertEquals(2, cakes.size());
    }

    @Test
    void deleteCake() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int id = 0;
        //
        boolean result = aut.deleteCake(id);
        //
        assertTrue(result);
    }

    @Test
    void deleteCake_Null() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int id = 1;
        boolean result = aut.deleteCake(id);
        assertFalse(result);
    }

    @Test
    void deleteCake_False_Fachnummer_Negative_Value() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int fachnummer = -10;
        //
        boolean result = aut.deleteCake(fachnummer);
        //
        assertFalse(result);
    }

    @Test
    void deleteCake_False_Fachnummer_False_Length() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int fachnummer = 10;
        //
        boolean result = aut.deleteCake(fachnummer);
        //
        assertFalse(result);
    }

    @Test
    void deleteCake_False_Fachnummer_True_Length() {
        Automat aut = new Automat(3);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(apfelkuchen);
        aut.insertCake(zitronenkuchen);
        aut.deleteCake(0);
        int fachnummer = 0;

        boolean result = aut.deleteCake(fachnummer);
        assertFalse(result);
    }

    @Test
    void deleteCake_HerstellerMap() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        aut.deleteCake(0);

        int count = aut.getHerstellerMap().get(hersteller);

        assertEquals(0, count);


    }


    @Test
    void inspectCake() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int id = 0;
        //
        boolean result = aut.inspectCake(id);
        //
        assertTrue(result);
    }

    @Test
    void inspectCake_Negative_Value() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int id = -1;
        //
        boolean result = aut.inspectCake(id);
        //
        assertFalse(result);
    }



    @Test
    void inspectCake_False_Fachnummer_False_Length() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        int fachnummer = 1;
        //
        boolean result = aut.inspectCake(fachnummer);
        //
        assertFalse(result);
    }

    @Test
    void inspectCake_False_Fachnummer_True_Length() {
        Automat aut = new Automat(3);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(apfelkuchen);
        aut.insertCake(zitronenkuchen);
        aut.deleteCake(0);
        //
        boolean result = aut.inspectCake(0);
        //
        assertFalse(result);
    }

    @Test
    void checkFachnummer() {
        Automat aut = new Automat(2);
        Hersteller h1 = new HerstellerImpl("Sonne");
        aut.insertHersteller(h1.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(h1, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(h1, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(zitronenkuchen);
        aut.insertCake(schokokuchen);

        int id = schokokuchen.getFachnummer();

        assertEquals(1, id);
    }

    @Test
    void insertHersteller() {
        Automat aut = new Automat(1);

        boolean result = aut.insertHersteller("Sonne");

        assertTrue(result);

    }

    @Test
    void insertHersteller_Number() {
        Automat aut = new Automat(1);
        Hersteller h1 = new HerstellerImpl("234");
        boolean result = aut.insertHersteller(h1.getName());
        assertFalse(result);
    }

    @Test
    void insertHersteller_Null() {
        Automat aut = new Automat(1);

        boolean result = aut.insertHersteller(null);
        assertFalse(result);

    }

    @Test
    void insertHersteller_IsEmpty() {
        Automat aut = new Automat(1);
        boolean result = aut.insertHersteller("");
        assertFalse(result);
    }

    @Test
    void insertCake_Hersteller_NoName() {
        Automat aut = new Automat(1);
        AbstractCake obstkuchen = mock(ObstkuchenImpl.class);
        Hersteller hersteller = mock(Hersteller.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(hersteller.getName()).thenReturn(" ");

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }

    @Test
    void insertCake_Hersteller_Null() {
        Automat aut = new Automat(1);
        AbstractCake obstkuchen = mock(ObstkuchenImpl.class);
        Hersteller hersteller = null;
        when(obstkuchen.getHersteller()).thenReturn(hersteller);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void getHerstellerMap() {
        Automat aut = new Automat(1);
        aut.insertHersteller("Sonne");

        Map<Hersteller, Integer> result = aut.getHerstellerMap();

        assertEquals(1, result.size());


    }

    @Test
    void doubleHersteller() {
        Automat aut = new Automat(1);
        aut.insertHersteller("Sonne");

        boolean result = aut.insertHersteller("Sonne");

        assertFalse(result);

    }

    @Test
    void deleteHersteller() {
        Automat aut = new Automat(4);
        aut.insertHersteller("Sonne");

        boolean result = aut.deleteHersteller("Sonne");
        aut.getHerstellerMap();

        assertTrue(result);

    }

    @Test
    void deleteHersteller_No_Name() {
        Automat aut = new Automat(1);
        aut.insertHersteller("Sonne");
        boolean result = aut.deleteHersteller("Mond");
        assertFalse(result);
    }


    @Test
    void deleteHersteller_Map_Check() {
        Automat aut = new Automat(4);
        aut.insertHersteller("Sonne");

        boolean result = aut.deleteHersteller("Sonne");
        Map<Hersteller, Integer> herstellerMap = aut.getHerstellerMap();

        assertEquals(0, herstellerMap.size());

    }


    @Test
    void getAllergen_In_Automat() {
        Automat aut = new Automat(4);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Gluten), "Apfel");
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        Set<Allergen> allergen = aut.getAllergen(true);

        assertEquals(2, allergen.size());


    }

    @Test
    void getAllergen_IsEmpty() {
        Automat aut = new Automat(4);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(), "Schokolade");
        aut.insertCake(schokokuchen);

        Set<Allergen> allergen = aut.getAllergen(true);

        assertEquals(0, allergen.size());

    }

    @Test
    void getCapacity() {
        Automat aut = new Automat(10);

        int capacity = 10;

        int result = aut.getCapacity();

        assertEquals(capacity, result);

    }

    @Test
    void getKeineAllergen_I_Automat() {
        Automat aut = new Automat(1);
        Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);

        Set<Allergen> result = aut.getAllergen(false);

        assertEquals(allAllergene, result);
    }

    @Test
    void deleteCake_Allergen() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        aut.deleteCake(0);

        Set<Allergen> result = aut.getAllergen(true);

        assertFalse(result.contains(Allergen.Gluten));

    }

    @Test
    void getAllergen_Ohne_Gluten() {
        Automat aut = new Automat(1);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Set.of(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        Set<Allergen> result = aut.getAllergen(false);

        assertFalse(result.contains(Allergen.Gluten));
    }

    @Test
    void swapFachnummer() {
        Automat aut = new Automat(2);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Schokolade");
        aut.insertCake(schokokuchen);
        aut.swapFachnummern(apfelkuchen.getFachnummer(), schokokuchen.getFachnummer());

        int result = apfelkuchen.getFachnummer();


        assertEquals(1, result);
    }

    @Test
    void copyFrom() {
        Automat aut = new Automat(3);

        Automat newAutomat = new Automat(4);
        newAutomat.copyFrom(aut);

        assertEquals(aut.getCapacity(), newAutomat.getCapacity());
    }
    @Test
    void copyFromNull(){
        Automat aut = new Automat(3);

        Automat newAutomat = new Automat(4);
        newAutomat.copyFrom(null);

        assertEquals(4, newAutomat.getCapacity());
    }


    @Test
    void createDto() {
        Automat aut = new Automat(3);

        AutomatDTO dto = aut.createDTO();

        assertEquals(3, dto.getCapacity());
    }

    @Test
    void createDtoCheckHersteller() {
        Automat aut = new Automat(3);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AutomatDTO dto = aut.createDTO();
        assertEquals(1, dto.getHerstellerListe().size());
    }

    @Test
    void createDtoCheckKuchen() {
        Automat aut = new Automat(3);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Schokolade");
        AbstractCake torte = new ObsttorteImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel", "Honig");
        aut.insertCake(schokokuchen);
        aut.insertCake(torte);
        AutomatDTO dto = aut.createDTO();
        assertEquals(3, dto.getCakes().size());
    }

    @Test
    void restoreFromDto() {
        Automat aut = new Automat(3);

        AutomatDTO dto = aut.createDTO();

        Automat newAutomat = new Automat(4);
        newAutomat.restoreFromDTO(dto);

        assertEquals(aut.getCapacity(), newAutomat.getCapacity());

    }

    @Test
    void restoreFromDto_Check_Hersteller() {
        Automat aut = new Automat(3);
        AutomatDTO dto = aut.createDTO();

        Automat newAutomat = new Automat(4);
        newAutomat.restoreFromDTO(dto);

        assertEquals(aut.getHerstellerMap().size(), newAutomat.getHerstellerMap().size());
    }

    @Test
    void restoreFromDto_Check_Kuchen() {
        Automat aut = new Automat(3);
        Hersteller hersteller = new HerstellerImpl("Sonne");
        aut.insertHersteller(hersteller.getName());
        AbstractCake apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Set.of(Allergen.Haselnuss), "Apfel");
        aut.insertCake(apfelkuchen);
        AutomatDTO dto = aut.createDTO();

        Automat newAutomat = new Automat(4);
        newAutomat.restoreFromDTO(dto);

        assertEquals(1, newAutomat.getListCake().size());
    }

    @Test
    void registerObserver() {
        Automat aut = new Automat(3);
        Beobachter beobachter = mock(Beobachter.class);
        aut.registerObserver(beobachter);
        assertEquals(1, aut.getObservers().size());
    }

    @Test
    void removeObserver() {
        Automat aut = new Automat(3);
        Beobachter beobachter = mock(Beobachter.class);
        Beobachter beobachter2 = mock(Beobachter.class);

        aut.registerObserver(beobachter);
        aut.registerObserver(beobachter2);
        aut.removeObserver(beobachter);
        assertEquals(1, aut.getObservers().size());
    }

    @Test
    void NotifyObservers() {
        Automat aut = new Automat(3);
        Beobachter observer = mock(Beobachter.class);
        aut.registerObserver(observer);

        aut.notifyObservers();

        verify(observer).update();
    }


}
