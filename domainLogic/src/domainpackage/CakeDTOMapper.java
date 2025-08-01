package domainpackage;


import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class CakeDTOMapper {
    public static CakeDTO toDTO(AbstractCake cake) {
        return cake.toDTO();
    }



    public static AbstractCake toCake(CakeDTO dto) {
        Hersteller h = new HerstellerImpl(dto.getHerstellerDTO().getName());
        List<Allergen> allergene = dto.getAllergene().stream().map(Allergen::valueOf).toList();
        Duration haltbarkeit = Duration.ofSeconds(dto.getHaltbarkeit());
        BigDecimal preis = new BigDecimal(dto.getPreis());
        int naehrwert = dto.getNaehrwert();
        List<String> extras = dto.getExtras();
        KuchenTyp typ = KuchenTyp.valueOf(dto.getKuchenTyp());

        AbstractCake cake = CakeFactory.createCake(typ, h, preis, naehrwert, haltbarkeit, allergene, extras);
        cake.setFachnummer(dto.getFachnummer());

        LocalDateTime localDate = LocalDateTime.parse(dto.getEinfuegedatum());
        cake.setEinfuegedatum(localDate);
        if (dto.getInspektionsdatum() != null) {
            Instant instant = Instant.parse(dto.getInspektionsdatum());
            Date date = Date.from(instant);
            cake.setInspektionsdatum(date);
        }
        return cake;
    }

}

