package domainpackage;

import kuchen.Allergen;
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
            default -> throw new IllegalArgumentException("Unbekannter Kuchentyp: " + typ);
        };
        return cake;
    }
}

//
//    public Obsttorte createObsttorte(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
//                                     Duration haltbarkeit, BigDecimal preis,
//                                     String obstsorte, String kremsorte) {
//        ObsttorteImpl obsttorte = new ObsttorteImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, obstsorte, kremsorte);
//
//        return obsttorte;
//    }
//
//    public Obstkuchen createObstkuchen(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
//                                       Duration haltbarkeit, BigDecimal preis,
//                                       String obstsorte) {
//        ObstkuchenImpl obstkuchen = new ObstkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, obstsorte);
//
//        return obstkuchen;
//    }
//
//    public Kremkuchen createKremkuchen(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
//                                       Duration haltbarkeit, BigDecimal preis,
//                                       String kremsorte) {
//        KremkuchenImpl kremkuchen = new KremkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, kremsorte);
//
//        return kremkuchen;
//    }

