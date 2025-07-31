package domainpackage.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutomatDTOTest {

    @Test
    void getCapacity() {
        AutomatDTO dto = new AutomatDTO();
        dto.setCapacity(10);
        assertEquals(10, dto.getCapacity());
    }

    @Test
    void setCapacity() {
        AutomatDTO dto = new AutomatDTO();
        dto.setCapacity(8);
        assertEquals(8, dto.getCapacity());
    }

    @Test
    void getHerstellerListe() {
        AutomatDTO dto = new AutomatDTO();
        HerstellerMapDTO hersteller = new HerstellerMapDTO();
        List<HerstellerMapDTO> herstellerListe = new ArrayList<>();
        herstellerListe.add(hersteller);
        dto.setHerstellerListe(herstellerListe);
        assertEquals(1,dto.getHerstellerListe().size());
    }

    @Test
    void setHerstellerListe() {
        AutomatDTO dto = new AutomatDTO();
        HerstellerMapDTO hersteller = new HerstellerMapDTO();
        List<HerstellerMapDTO> herstellerListe = new ArrayList<>();
        herstellerListe.add(hersteller);
        dto.setHerstellerListe(herstellerListe);
        assertEquals(1,dto.getHerstellerListe().size());
    }

    @Test
    void getAllergene() {
        AutomatDTO dto = new AutomatDTO();
        List<String> allergene = new ArrayList<>();
        allergene.add("Allergene");
        dto.setAllergene(allergene);
        assertEquals(1,dto.getAllergene().size());
    }

    @Test
    void setAllergene() {
        AutomatDTO dto = new AutomatDTO();
        List<String> allergene = new ArrayList<>();
        allergene.add("Allergene");
        dto.setAllergene(allergene);
        assertEquals(1,dto.getAllergene().size());
    }

    @Test
    void getCakes() {
        AutomatDTO dto = new AutomatDTO();
        List<CakeDTO> cakes = new ArrayList<>();
        CakeDTO cake = new CakeDTO();
        cakes.add(cake);
        dto.setCakes(cakes);
        assertEquals(1,dto.getCakes().size());
    }

    @Test
    void setCakes() {
        AutomatDTO dto = new AutomatDTO();
        List<CakeDTO> cakes = new ArrayList<>();
        CakeDTO cake = new CakeDTO();
        cakes.add(cake);
        dto.setCakes(cakes);
        assertEquals(1,dto.getCakes().size());
    }
}