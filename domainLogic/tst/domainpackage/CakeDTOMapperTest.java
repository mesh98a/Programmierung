package domainpackage;

import domainpackage.dto.CakeDTO;
import domainpackage.dto.HerstellerDTO;
import kuchen.Allergen;
import org.junit.jupiter.api.Test;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CakeDTOMapperTest {

    @Test
    void toDTO() {
        Hersteller hersteller = new HerstellerImpl("Sonne");
        KremkuchenImpl kuchen = new KremkuchenImpl(hersteller, new BigDecimal("12.00"), 350, Duration.ofDays(5), Arrays.asList(Allergen.Haselnuss), "Honig");
        CakeDTO cakeDTO = CakeDTOMapper.toDTO(kuchen);

       assertEquals(350,cakeDTO.getNaehrwert());
    }

    @Test
    void toCake() {
        CakeDTO cakeDTO = new CakeDTO();
        HerstellerDTO hersteller = new HerstellerDTO("Sonne");
        cakeDTO.setHerstellerDTO(hersteller);
        List<String> allergene = new ArrayList<>();
        allergene.add("Gluten");
        cakeDTO.setAllergene(allergene);
        cakeDTO.setNaehrwert(350);
        cakeDTO.setHaltbarkeit(100l);
        cakeDTO.setPreis("10");
        cakeDTO.setEinfuegedatum("2024-07-12T15:30");
        cakeDTO.setFachnummer(1);
        cakeDTO.setKuchenTyp("OBSTKUCHEN");
        List<String> extras = new ArrayList<>();
        extras.add("Apfel");
        cakeDTO.setExtras(extras);
        AbstractCake cake = CakeDTOMapper.toCake(cakeDTO);
        assertEquals(350,cake.getNaehrwert());
    }
}