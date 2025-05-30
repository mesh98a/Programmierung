package domainpackage;

import kuchen.Allergen;
import kuchen.Obstkuchen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class ObstkuchenImpl extends AbstractCake implements Obstkuchen {
    private String obstsorte;

    public ObstkuchenImpl(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
                          Duration haltbarkeit, BigDecimal preis,
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
}

