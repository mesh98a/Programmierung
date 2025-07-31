package domainpackage.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CakeDTOTest {

    @Test
    void getHerstellerDTO() {
        CakeDTO cakeDTO = new CakeDTO();
        HerstellerDTO hersteller = new HerstellerDTO("Sonne");
        cakeDTO.setHerstellerDTO(hersteller);
        assertEquals("Sonne", cakeDTO.getHerstellerDTO().getName());
    }

    @Test
    void setHerstellerDTO() {
        CakeDTO cakeDTO = new CakeDTO();
        HerstellerDTO hersteller = new HerstellerDTO("Sonne");
        cakeDTO.setHerstellerDTO(hersteller);
        assertEquals("Sonne", cakeDTO.getHerstellerDTO().getName());
    }

    @Test
    void getAllergene() {
        CakeDTO cakeDTO = new CakeDTO();
        List<String> allergene = new ArrayList<>();
        allergene.add("Allergene");
        cakeDTO.setAllergene(allergene);
        assertEquals(allergene, cakeDTO.getAllergene());
    }

    @Test
    void setAllergene() {
        CakeDTO cakeDTO = new CakeDTO();
        List<String> allergene = new ArrayList<>();
        allergene.add("Allergene");
        cakeDTO.setAllergene(allergene);
        assertEquals(allergene, cakeDTO.getAllergene());
    }

    @Test
    void getNaehrwert() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setNaehrwert(10);
        assertEquals(10, cakeDTO.getNaehrwert());
    }

    @Test
    void setNaehrwert() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setNaehrwert(10);
        assertEquals(10, cakeDTO.getNaehrwert());
    }

    @Test
    void getHaltbarkeit() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setHaltbarkeit(100l);
        assertEquals(100, cakeDTO.getHaltbarkeit());
    }

    @Test
    void setHaltbarkeit() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setHaltbarkeit(100l);
        assertEquals(100, cakeDTO.getHaltbarkeit());
    }

    @Test
    void getPreis() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setPreis("10");
        assertEquals("10", cakeDTO.getPreis());
    }

    @Test
    void setPreis() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setPreis("10");
        assertEquals("10", cakeDTO.getPreis());
    }

    @Test
    void getInspektionsdatum() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setInspektionsdatum("2025");
        assertEquals("2025", cakeDTO.getInspektionsdatum());
    }

    @Test
    void setInspektionsdatum() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setInspektionsdatum("2025");
        assertEquals("2025", cakeDTO.getInspektionsdatum());
    }

    @Test
    void getFachnummer() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setFachnummer(1);
        assertEquals(1, cakeDTO.getFachnummer());
    }

    @Test
    void setFachnummer() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setFachnummer(1);
        assertEquals(1, cakeDTO.getFachnummer());
    }

    @Test
    void getEinfuegedatum() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setEinfuegedatum("2025");
        assertEquals("2025", cakeDTO.getEinfuegedatum());
    }

    @Test
    void setEinfuegedatum() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setEinfuegedatum("2025");
        assertEquals("2025", cakeDTO.getEinfuegedatum());
    }

    @Test
    void getKuchenTyp() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setKuchenTyp("Obst");
        assertEquals("Obst", cakeDTO.getKuchenTyp());
    }


    @Test
    void setKuchenTyp() {
        CakeDTO cakeDTO = new CakeDTO();
        cakeDTO.setKuchenTyp("Obst");
        assertEquals("Obst", cakeDTO.getKuchenTyp());
    }

    @Test
    void getExtras() {
        CakeDTO cakeDTO = new CakeDTO();
        List<String> extras = new ArrayList<>();
        extras.add("Extras");
        cakeDTO.setExtras(extras);
        assertEquals(1, cakeDTO.getExtras().size());
    }

    @Test
    void setExtras() {
        CakeDTO cakeDTO = new CakeDTO();
        List<String> extras = new ArrayList<>();
        extras.add("Extras");
        cakeDTO.setExtras(extras);
        assertEquals(1, cakeDTO.getExtras().size());
    }
}