package domainpackage.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerMapDTOTest {

    @Test
    void getHersteller() {
        HerstellerMapDTO map = new HerstellerMapDTO();
        map.setHersteller(new HerstellerDTO("Hersteller"));
        assertEquals("Hersteller", map.getHersteller().getName());
    }

    @Test
    void setHersteller() {
        HerstellerMapDTO map = new HerstellerMapDTO();
        map.setHersteller(new HerstellerDTO("Hersteller"));
        assertEquals("Hersteller", map.getHersteller().getName());
    }

    @Test
    void getAnzahl() {
        HerstellerMapDTO map = new HerstellerMapDTO();
        map.setAnzahl(3);
        assertEquals(3, map.getAnzahl());
    }

    @Test
    void setAnzahl() {
        HerstellerMapDTO map = new HerstellerMapDTO();
        map.setAnzahl(3);
        assertEquals(3, map.getAnzahl());
    }
}