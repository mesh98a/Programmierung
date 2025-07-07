package domainpackage.dto;

import java.io.Serializable;
import java.util.List;

public class AutomatDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer capacity;
    private List<HerstellerMapDTO> herstellerListe;
    private List<String> allergene;
    private List<CakeDTO> cakes;

    public AutomatDTO() {
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<HerstellerMapDTO> getHerstellerListe() {
        return herstellerListe;
    }

    public void setHerstellerListe(List<HerstellerMapDTO> herstellerListe) {
        this.herstellerListe = herstellerListe;
    }

    public List<String> getAllergene() {
        return allergene;
    }

    public void setAllergene(List<String> allergene) {
        this.allergene = allergene;
    }

    public List<CakeDTO> getCakes() {
        return cakes;
    }

    public void setCakes(List<CakeDTO> cakes) {
        this.cakes = cakes;
    }
}
