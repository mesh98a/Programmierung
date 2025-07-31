package domainpackage.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerDTOTest {

    @Test
    void getName() {
        HerstellerDTO herstellerDTO = new HerstellerDTO();
        herstellerDTO.setName("Hersteller");
        assertEquals("Hersteller", herstellerDTO.getName());
    }

    @Test
    void setName() {
        HerstellerDTO herstellerDTO = new HerstellerDTO();
        herstellerDTO.setName("Hersteller");
        assertEquals("Hersteller", herstellerDTO.getName());
    }
}