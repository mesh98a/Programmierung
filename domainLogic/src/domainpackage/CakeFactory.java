package domainpackage;

import kuchen.Allergen;
import kuchen.KuchenTyp;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class CakeFactory {

    public static AbstractCake  createCake(KuchenTyp typ, Hersteller hersteller, BigDecimal preis, int naehrwert,
                                           Duration haltbarkeit, Collection<Allergen> allergenSet, List<String> kuchensorten) {
        AbstractCake cake = null;
        cake = switch (typ) {
            case OBSTKUCHEN -> new ObstkuchenImpl(hersteller, preis, naehrwert, haltbarkeit, allergenSet, kuchensorten.get(0));
            case KREMKUCHEN -> new KremkuchenImpl(hersteller, preis, naehrwert, haltbarkeit, allergenSet, kuchensorten.get(0));
            case OBSTTORTE -> new ObsttorteImpl(hersteller, preis, naehrwert, haltbarkeit, allergenSet, kuchensorten.get(0), kuchensorten.get(1));
        };
        return cake;
    }
}

