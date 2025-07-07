package domainpackage;

import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Obstkuchen;
import domainpackage.dto.CakeDTO;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ObstkuchenImpl extends AbstractCake implements Obstkuchen {
    private String obstsorte;

    public ObstkuchenImpl(Hersteller hersteller,BigDecimal preis, int naehrwert,
                          Duration haltbarkeit,Collection<Allergen> allergene,
                          String obstsorte) {
        super(hersteller, allergene, naehrwert, haltbarkeit, preis);
        this.obstsorte = obstsorte;
    }

    @Override
    public String getObstsorte() {
        return obstsorte;
    }

    @Override
    public KuchenTyp getKuchenTyp() {
        return KuchenTyp.OBSTKUCHEN;
    }

    @Override
    protected void fillCommonFields(CakeDTO dto) {
        super.fillCommonFields(dto);
        dto.setExtras(new ArrayList<>(Arrays.asList(this.obstsorte)));
    }
}

