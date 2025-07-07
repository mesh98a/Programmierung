package domainpackage.dto;

public class HerstellerDTO {
    private String name;

    public HerstellerDTO() {}

    public HerstellerDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
