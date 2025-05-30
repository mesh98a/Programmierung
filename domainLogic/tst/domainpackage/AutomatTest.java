package domainpackage;

import kuchen.Allergen;
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
        this.apfelkuchen = new ObstkuchenImpl(hersteller, Arrays.asList(Allergen.Gluten), 300, Duration.ofDays(5), new BigDecimal("10.00"), "Apfel");
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
    void testInsertCakeNullHersteller(){
        Automat aut = new Automat(10);

        boolean result = aut.insertCake(apfelkuchen);

        assertFalse(result);
    }

    @Test
    void testInsertCakeAutomatIsFull() {
        Automat aut = new Automat(0);
        //
        boolean result = aut.insertCake(apfelkuchen);
        //
        assertFalse(result);
    }


    @Test
    void insertionDate(){
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.insertCake(apfelkuchen);

        assertTrue(result);
        assertNotNull(apfelkuchen.getEinfuegedatum());


    }


    @Test
    void testDisplayListCake() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        //
        List<AbstractCake> kuchenListe = aut.displayListCake();
        //
        assertEquals(1, kuchenListe.size());
    }
    @Test
    void testFilterCake() {
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"),  "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, Arrays.asList(Allergen.Erdnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"), "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        KuchenTyp typ = KuchenTyp.OBSTKUCHEN;

        List<AbstractCake> kuchenListe = aut.displayListCake(typ);

        assertEquals(2, kuchenListe.size());
    }

    @Test
    void testDisplayListCakeWithTwoDifferentCakes() {
        Automat aut = new Automat(2);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        Hersteller hersteller2 = new HerstellerImpl("Bäckerei Schmidt");
        aut.insertHersteller(hersteller2.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller2, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"),"Schokolade");
        aut.insertCake(schokokuchen);
        //
        List<AbstractCake> cakes = aut.displayListCake();
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
    void insertHersteller() {
        Automat aut = new Automat(1);

        boolean result = aut.insertHersteller(hersteller.getName());

        assertTrue(result);

    }

    @Test
    void getHerstellerMap() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());

        Map<Hersteller, Integer> result = aut.getHerstellerMap();

        assertEquals(1, result.size());


    }

    @Test
    void testCheckFachnummer() {
        Automat aut = new Automat(2);
        Hersteller h1 = new HerstellerImpl("Sonne");
        aut.insertHersteller(h1.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(h1, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(h1, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"),  "Zitrone");
        aut.insertCake(zitronenkuchen);
        aut.insertCake(schokokuchen);

        int id = schokokuchen.getFachnummer();

        assertEquals(1, id);
    }
    @Test
    void testDoubleHersteller() {
        Automat aut = new Automat(1);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.insertHersteller(hersteller.getName());

        assertFalse(result);

    }
    @Test
    void deleteHersteller(){
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.deleteHersteller(hersteller.getName());
        Map<Hersteller,Integer> herstellerMap = aut.getHerstellerMap();

        assertTrue(result);

    }
    @Test
    void deleteMapHersteller(){
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());

        boolean result = aut.deleteHersteller(hersteller.getName());
        Map<Hersteller,Integer> herstellerMap = aut.getHerstellerMap();

        assertEquals(0,herstellerMap.size());

    }


    @Test
    void testInsert_Hersteller_NoName() {
        Automat aut = new Automat(1);
        AbstractCake obstkuchen = mock(ObstkuchenImpl.class);
        Hersteller hersteller = mock(Hersteller.class);
        when(obstkuchen.getHersteller()).thenReturn(hersteller);
        when(hersteller.getName()).thenReturn(" ");

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);

    }

    @Test
    void testInsert_Hersteller_Null() {
        Automat aut = new Automat(1);
        AbstractCake obstkuchen = mock(ObstkuchenImpl.class);
        Hersteller hersteller = null;
        when(obstkuchen.getHersteller()).thenReturn(hersteller);

        boolean result = aut.insertCake(obstkuchen);

        assertFalse(result);
    }

    @Test
    void getAllergenInAutomat(){
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"), "Schokolade");
        AbstractCake zitronenkuchen = new ObstkuchenImpl(hersteller, Arrays.asList(Allergen.Haselnuss), 350, Duration.ofDays(5), new BigDecimal("12.00"),  "Zitrone");
        aut.insertCake(schokokuchen);
        aut.insertCake(zitronenkuchen);
        aut.insertCake(apfelkuchen);

        Set<Allergen> allergen  = aut.displayAllergen();

        assertEquals(2,allergen.size());


    }
    @Test
    void testGetAllergenIsEmpty(){
        Automat aut = new Automat(4);
        aut.insertHersteller(hersteller.getName());
        AbstractCake schokokuchen = new KremkuchenImpl(hersteller, Arrays.asList(), 350, Duration.ofDays(5), new BigDecimal("12.00"), "Schokolade");
        aut.insertCake(schokokuchen);

        Set<Allergen> allergen  = aut.displayAllergen();

        assertEquals(0,allergen.size());

    }

    @Test
    void testGetCapacity(){
        Automat aut = new Automat(10);
        aut.insertHersteller(hersteller.getName());
        aut.insertCake(apfelkuchen);
        int capacity = 9;

        int result = aut.getFreeCapacity();

        assertEquals(capacity,result);

    }
    @Test
    void testGetKeineAllergen(){
        Automat aut = new Automat(1);
        Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);

        Set<Allergen> result = aut.displaykeineAllergen();

        assertEquals(allAllergene,result);
    }
    @Test
    void testGetKeineAllergenOhneGluten(){
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

//    @Test
//    void testInsert_Allergen_Null() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getAllergene()).thenReturn(null);
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//    }

//    @Test
//    void testInsert_Naehrwert_TooSmall() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getNaehrwert()).thenReturn(-1);
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//
//    }
//
//    @Test
//    void testInsert_Haltbarkeit_Null() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getHaltbarkeit()).thenReturn(null);
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testInsert_Haltbarkeit_TooSmall() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getHaltbarkeit()).thenReturn(Duration.ofDays(0));
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testInsert_Preis_Null() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getPreis()).thenReturn(null);
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testInsert_Preis_TooSmall() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getPreis()).thenReturn(new BigDecimal("-1.00"));
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//
//    }
//
//    @Test
//    void testInsert_Preis_Too_Many_Decimals() {
//        Automat aut = new Automat(1);
//        AbstractCake cake = createValidMockCake();
//        when(cake.getPreis()).thenReturn(new BigDecimal("1.999"));
//
//        boolean result = aut.createCake(cake);
//
//        assertFalse(result);
//
//    }
//
//    @Test
//    void testInsert_Obstsorte_Null() {
//        Automat aut = new Automat(1);
//        ObstkuchenImpl obstkuchen = mock(ObstkuchenImpl.class);
//        Hersteller hersteller = mock(Hersteller.class);
//        when(obstkuchen.getHersteller()).thenReturn(hersteller);
//        when(hersteller.getName()).thenReturn("Bäckerei");
//        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
//        when(obstkuchen.getNaehrwert()).thenReturn(100);
//        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
//        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
//        when(obstkuchen.getObstsorte()).thenReturn(null);
//
//        boolean result = aut.createCake(obstkuchen);
//
//        assertFalse(result);
//
//    }
//
//    @Test
//    void testInsert_Kremsorte_Null() {
//        Automat aut = new Automat(1);
//        KremkuchenImpl obstkuchen = mock(KremkuchenImpl.class);
//        Hersteller hersteller = mock(Hersteller.class);
//        when(obstkuchen.getHersteller()).thenReturn(hersteller);
//        when(hersteller.getName()).thenReturn("Bäckerei");
//        when(obstkuchen.getAllergene()).thenReturn(Arrays.asList(Allergen.Gluten));
//        when(obstkuchen.getNaehrwert()).thenReturn(100);
//        when(obstkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3));
//        when(obstkuchen.getPreis()).thenReturn(new BigDecimal("10.00"));
//        when(obstkuchen.getKremsorte()).thenReturn(null);
//
//        boolean result = aut.createCake(obstkuchen);
//
//        assertFalse(result);
//
//    }



}
