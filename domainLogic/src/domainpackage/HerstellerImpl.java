package domainpackage;

import verwaltung.Hersteller;

import java.io.Serializable;

public class HerstellerImpl implements Hersteller, Serializable {
    static final long serialVersionUID=1L;
    private String name;

    public HerstellerImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass().equals(HerstellerImpl.class)
                && this.name.equals(((HerstellerImpl) obj).name);
    }
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
}

