package domainpackage;

import kuchen.Allergen;
import kuchen.Kuchen;
import verwaltung.Hersteller;
import verwaltung.Verkaufsobjekt;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


// https://link.springer.com/book/10.1007/978-3-662-66582-4
public abstract class AbstractCake implements Kuchen, Verkaufsobjekt {
    //protected final Clock clock;
    protected Hersteller hersteller;
    protected Collection<Allergen> allergene;
    protected int naehrwert;
    protected Duration haltbarkeit;
    protected BigDecimal preis;
    protected Date inspektionsdatum;
    protected int fachnummer;
    protected LocalDateTime einfuegedatum;

    public AbstractCake(Hersteller hersteller, Collection<Allergen> allergene, int naehrwert,
                        Duration haltbarkeit, BigDecimal preis) {
        this.hersteller = hersteller;
        this.allergene = allergene;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;
        //this.inspektionsdatum = inspektionsdatum;
        //this.insertionDate = Date.from(clock.instant());

    }
    public abstract KuchenTyp getKuchenTyp();


    @Override
    public Hersteller getHersteller() {
        return this.hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return this.allergene;
    }

    @Override
    public int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }

    @Override
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return this.inspektionsdatum;
    }

    @Override
    public int getFachnummer() {
        return this.fachnummer;
    }

    public Date getInsertionDate(){
        return this.getInsertionDate();
    }

    public void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    protected void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    protected void setEinfuegedatum(LocalDateTime date){
        this.einfuegedatum = date;
    }

    public LocalDateTime getEinfuegedatum() {
        return this.einfuegedatum;
    }

}
