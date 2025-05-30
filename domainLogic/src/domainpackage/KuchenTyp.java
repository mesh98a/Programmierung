package domainpackage;

import kuchen.Allergen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public enum KuchenTyp {
    OBSTKUCHEN ,
//    {
//        @Override
//        public AbstractCake createCake(Hersteller hersteller, Collection<Allergen> allergene,
//                                       int naehrwert, Duration haltbarkeit,
//                                       BigDecimal preis, String... extras) {
//            return new ObstkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, extras[0]);
//        }
    KREMKUCHEN ,
    OBSTTORTE
//
//    public abstract AbstractCake createCake(Hersteller hersteller, Collection<Allergen> allergene,
//                                            int naehrwert, Duration haltbarkeit,
//                                            BigDecimal preis, String... extras);
}

