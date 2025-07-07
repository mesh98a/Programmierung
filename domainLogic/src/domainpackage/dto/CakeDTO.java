package domainpackage.dto;

import java.util.List;

public class CakeDTO {

    private HerstellerDTO hersteller;
    private String preis;
    private Integer naehrwert;
    private Long haltbarkeit;
    private List<String> allergene;
    private String inspektionsdatum;
    private Integer fachnummer;
    private String einfuegedatum;
    private String kuchenTyp;
    private List<String> extras;

    public CakeDTO() {
    }

    // Getter und Setter

    public HerstellerDTO getHerstellerDTO() {
        return hersteller;
    }

    public void setHerstellerDTO(HerstellerDTO hersteller) {
        this.hersteller = hersteller;
    }

    public List<String> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<String> allergene) {
        this.allergene = allergene;
    }

    public Integer getNaehrwert() {
        return naehrwert;
    }

    public void setNaehrwert(Integer naehrwert) {
        this.naehrwert = naehrwert;
    }

    public Long getHaltbarkeit() {
        return haltbarkeit;
    }

    public void setHaltbarkeit(Long haltbarkeit) {
        this.haltbarkeit = haltbarkeit;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getInspektionsdatum() {
        return inspektionsdatum;
    }

    public void setInspektionsdatum(String inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    public Integer getFachnummer() {
        return fachnummer;
    }

    public void setFachnummer(Integer fachnummer) {
        this.fachnummer = fachnummer;
    }

    public String getEinfuegedatum() {
        return einfuegedatum;
    }

    public void setEinfuegedatum(String einfuegedatum) {
        this.einfuegedatum = einfuegedatum;
    }

    public String getKuchenTyp() {
        return kuchenTyp;
    }

    public void setKuchenTyp(String kuchenTyp) {
        this.kuchenTyp = kuchenTyp;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

}
