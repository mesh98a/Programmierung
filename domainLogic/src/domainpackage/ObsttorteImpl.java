package domainpackage;

import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Obsttorte;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ObsttorteImpl extends AbstractCake implements Obsttorte {
    private String obstsorte;
    private String kremsorte;

    public ObsttorteImpl(Hersteller hersteller,BigDecimal preis, int naehrwert,
                         Duration haltbarkeit,Collection<Allergen> allergene,String obstsorte, String kremsorte) {
        super(hersteller, allergene, naehrwert, haltbarkeit, preis);
        this.obstsorte = obstsorte;
        this.kremsorte = kremsorte;
    }

    @Override
    public KuchenTyp getKuchenTyp() {
        return KuchenTyp.OBSTTORTE;
    }

    @Override
    public String getKremsorte() {
        return this.kremsorte;
    }

    @Override
    public String getObstsorte() {
        return this.obstsorte;
    }

    @Override
    protected CakeDTO toDTO() {
        CakeDTO dto = super.toDTO();
        dto.setExtras(new ArrayList<>(Arrays.asList(this.obstsorte,this.kremsorte)));
        return dto;
    }
}
