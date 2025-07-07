package eventsimpl.automatevent;

import eventsystem.automatsystem.AutomatEvent;
import kuchen.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class InsertCakeEvent extends AutomatEvent {
    private String kuchentyp;
    private String herstellerName;
    private BigDecimal preis;
    private int naehrwert;
    private Duration haltbarkeit;
    private Collection<Allergen> allergene;
    private List<String> kuchensorten;

    public InsertCakeEvent(Object source,String kuchentyp, String herstellerName, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergenSet, List<String> kuchensorten) {
        super(source);
        this.kuchentyp = kuchentyp;
        this.herstellerName = herstellerName;
        this.preis = preis;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.allergene = allergenSet;
        this.kuchensorten = kuchensorten;
    }

    public String getKuchentyp() {
        return kuchentyp;
    }

    public String getHerstellerName() {
        return herstellerName;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public int getNaehrwert() {
        return naehrwert;
    }

    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    public List<String> getKuchensorten() {
        return kuchensorten;
    }


}
