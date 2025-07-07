package domainpackage;

import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Kuchenprodukt;
import kuchen.Obstkuchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutomatTest {

    public Hersteller hersteller;
    public ObstkuchenImpl apfelkuchen;

    @BeforeEach
    void setUp() {
        this.hersteller = new HerstellerImpl("Bäckerei Müller");
        this.apfelkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("10.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Gluten), "Apfel");
    }

    @Test
    void testInsertCake() {
        //setup
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        // Methode unter Test
        boolean result = aut.insertCake(apfelkuchen);
        // zusicherung
        assertTrue(result);
    }

    @Test
    void testInsertCakeNullHersteller() {
        Automat aut = new Automat(10);

        boolean result = aut.insertCake(apfelkuchen);

        assertFalse(result);
    }

    @Test
    void testInsertCakeAutomatIsFull() {
        Automat aut = new Automat(0);
        aut.insertHersteller(hersteller.getName());
        //
        boolean result = aut.insertCake(apfelkuchen);
        //
        assertFalse(result);
    }

    @Test
    void testInsert_Naehrwert_TooSmall() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(-100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }

    @Test
    void testInsert_Haltbarkeit_Null() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(null);
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void testInsert_Haltbarkeit_TooSmall() {
        Automat aut = new Automat(1);
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
    void testInsert_Preis_Null() {
        Automat aut = new Automat(1);
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
    void testInsert_Preis_TooSmall() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(-100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }


    @Test
    void testInsertionDate() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);

        assertNotNull(apfelkuchen.getEinfuegedatum());

    }


    @Test
    void testGetListCake() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        //
        List<Kuchenprodukt> kuchenListe = aut.getListCake();
        //
        assertEquals(1, kuchenListe.size());
    }

    @Test
    void testFilterCake() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("10.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Erdnuss), "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        KuchenTyp typ = KuchenTyp.OBSTKUCHEN;

        List<Kuchenprodukt> kuchenListe = aut.getListCake(typ);

        assertEquals(2, kuchenListe.size());
    }

    @Test
    void testGetListCakeWithTwoDifferentCakes() {
        Automat aut = new Automat(2);
        aut.insertHersteller(hersteller.getName());
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
    void testDeleteCake() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int id = 0;
        //
        boolean result = aut.deleteCake(id);
        //
        assertTrue(result);
    }

    @Test
    void testDeleteCakeFalseFachnummer() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int fachnummer = 10;
        //
        boolean result = aut.deleteCake(fachnummer);
        //
        assertFalse(result);
    }

    @Test
    void testDeleteCakeHerstellerMap() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        aut.deleteCake(0);

        int count = aut.getHerstellerMap().get(hersteller);

        assertEquals(0, count);


    }

    @Test
    void testInspectCake() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int id = 0;
        //
        boolean result = aut.inspectCake(id);
        //
        assertTrue(result);
    }


    @Test
    void testInspectCakeFalseFachnummer() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int fachnummer = 1;
        //
        boolean result = aut.inspectCake(fachnummer);
        //
        assertFalse(result);
    }

    @Test
    void testCheckFachnummer() {
        Automat aut = new Automat(2);
        Hersteller h1 = new HerstellerImpl("Sonne");
        aut.insertHersteller(h1.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(h1, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(h1, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(zitronenkuchen);
        aut.insertCake(schokokuchen);

        int id = schokokuchen.getFachnummer();

        assertEquals(1, id);
    }

    @Test
    void insertHersteller() {
        Automat aut = new Automat(1);

        boolean result = aut.insertHersteller(hersteller.getName());

        assertTrue(result);

    }

    @Test
    void testInsertCake_Hersteller_NoName() {
        Automat aut = new Automat(1);
        AbstractCake obstkuchen = mock(ObstkuchenImpl.class);
        Hersteller hersteller = mock(Hersteller.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(hersteller.getName()).thenReturn(" ");

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }

    @Test
    void testInsertCake_Hersteller_Null() {
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
        aut.insertHersteller(hersteller.getName());

        Map<Hersteller, Integer> result = aut.getHerstellerMap();

        assertEquals(1, result.size());


    }

    @Test
    void testDoubleHersteller() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.insertHersteller(hersteller.getName());

        assertFalse(result);

    }

    @Test
    void deleteHersteller() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.deleteHersteller(hersteller.getName());
        aut.getHerstellerMap();

        assertTrue(result);

    }

    @Test
    void deleteMapHersteller() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.deleteHersteller(hersteller.getName());
        Map<Hersteller, Integer> herstellerMap = aut.getHerstellerMap();

        assertEquals(0, herstellerMap.size());

    }


    @Test
    void getAllergenInAutomat() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        Set<Allergen> allergen = aut.getAllergen();

        assertEquals(2, allergen.size());


    }

    @Test
    void testGetAllergenIsEmpty() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(), "Schokolade");
        aut.insertCake(schokokuchen);

        Set<Allergen> allergen = aut.getAllergen();

        assertEquals(0, allergen.size());

    }

    @Test
    void testGetCapacity() {
        Automat aut = new Automat(10);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int capacity = 9;

        int result = aut.getFreeCapacity();

        assertEquals(capacity, result);

    }

    @Test
    void testGetKeineAllergen() {
        Automat aut = new Automat(1);
        Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);

        Set<Allergen> result = aut.displaykeineAllergen();

        assertEquals(allAllergene, result);
    }

    @Test
    void deleteCakeAllergen() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        aut.deleteCake(0);

        Set<Allergen> result = aut.getAllergen();

        assertFalse(result.contains(Allergen.Gluten));

    }

    @Test
    void testGetKeineAllergenOhneGluten() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstkuchen.getNaehrwert()).thenReturn(100);
        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
        when(obstkuchen.getObstsorte()).thenReturn("Apfel");
        aut.insertCake(obstkuchen);

        Set<Allergen> result = aut.displaykeineAllergen();

        assertFalse(result.contains(Allergen.Gluten));
    }

    @Test
    void testSwapFachnummer() {
        Automat aut = new Automat(2);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Schokolade");
        aut.insertCake(schokokuchen);
        aut.swapFachnummern(apfelkuchen.getFachnummer(), schokokuchen.getFachnummer());

        int result = apfelkuchen.getFachnummer();


        assertEquals(1, result);
    }


}
