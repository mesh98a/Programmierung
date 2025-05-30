package domainpackage;

import kuchen.Allergen;
import kuchen.Obsttorte;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class ObsttorteImpl extends AbstractCake implements Obsttorte {
    private String obstsorte;
    private String kremsorte;

    public ObsttorteImpl(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert, Duration haltbarkeit, BigDecimal preis,String obstsorte, String kremsorte) {
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
}
