package domainpackage;

import kuchen.Allergen;
import kuchen.Kremkuchen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class KremkuchenImpl extends AbstractCake implements Kremkuchen {

    private String kremsorte;

    public KremkuchenImpl(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
                          Duration haltbarkeit, BigDecimal preis,
                           String kremsorte) {
        super(hersteller, allergene, naehrwert, haltbarkeit, preis);
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }

    @Override
    public KuchenTyp getKuchenTyp() {
        return KuchenTyp.KREMKUCHEN;
    }
}
