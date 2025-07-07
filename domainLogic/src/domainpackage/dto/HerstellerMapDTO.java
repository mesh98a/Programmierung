package domainpackage.dto;

public class HerstellerMapDTO {
    private HerstellerDTO hersteller;
    private Integer anzahl;

    public HerstellerMapDTO() {}

    public HerstellerMapDTO(HerstellerDTO hersteller, Integer anzahl){
        this.hersteller = hersteller;
        this.anzahl = anzahl;
    }

    public HerstellerDTO getHersteller() {
        return hersteller;
    }
    public void setHersteller(HerstellerDTO hersteller) {
        this.hersteller = hersteller;
    }
    public Integer getAnzahl() {
        return anzahl;
    }
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }
}
