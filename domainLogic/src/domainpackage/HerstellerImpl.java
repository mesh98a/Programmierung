package domainpackage;

import verwaltung.Hersteller;

public class HerstellerImpl implements Hersteller {
    private final String name;

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

