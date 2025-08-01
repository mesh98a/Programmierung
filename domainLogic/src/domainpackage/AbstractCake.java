package domainpackage;

import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Kuchenprodukt;
import domainpackage.dto.CakeDTO;
import domainpackage.dto.HerstellerDTO;
import verwaltung.Hersteller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public abstract class AbstractCake implements Kuchenprodukt, Serializable {
    static final long serialVersionUID = 1L;
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
        this.einfuegedatum = LocalDateTime.now();

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
        if (this.haltbarkeit == null) {
            return this.haltbarkeit;
        }
        LocalDateTime ablauf = this.einfuegedatum.plus(this.haltbarkeit);
        LocalDateTime jetzt = LocalDateTime.now();
        if (jetzt.isAfter(ablauf)) {
            return Duration.ZERO;
        }
        return Duration.between(jetzt, ablauf);
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

    void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    void setEinfuegedatum(LocalDateTime date) {
        this.einfuegedatum = date;
    }

    public LocalDateTime getEinfuegedatum() {
        return this.einfuegedatum;
    }

    protected CakeDTO toDTO() {
        CakeDTO dto = new CakeDTO();
        dto.setKuchenTyp(this.getKuchenTyp().name());

        HerstellerDTO hDto = new HerstellerDTO();
        hDto.setName(this.hersteller.getName());
        dto.setHerstellerDTO(hDto);

        dto.setAllergene(new ArrayList<>(this.allergene.stream().map(Enum::name).toList()));
        dto.setNaehrwert(this.naehrwert);
        dto.setHaltbarkeit(this.haltbarkeit.getSeconds());
        dto.setPreis(this.preis.toPlainString());

        dto.setFachnummer(this.fachnummer);
        if (inspektionsdatum != null)
            dto.setInspektionsdatum(inspektionsdatum.toInstant().toString());
        if (einfuegedatum != null)
            dto.setEinfuegedatum(einfuegedatum.toString());
        return dto;
    }
}
