package domainpackage;

import domainpackage.dto.CakeDTO;
import kuchen.Allergen;
import kuchen.Kremkuchen;
import kuchen.KuchenTyp;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class KremkuchenImpl extends AbstractCake implements Kremkuchen {

    private String kremsorte;

    public KremkuchenImpl(Hersteller hersteller,BigDecimal preis, int naehrwert,
                          Duration haltbarkeit,Collection<Allergen> allergene,
                           String kremsorte) {
        super(hersteller, allergene, naehrwert, haltbarkeit, preis);
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }

    @Override
    public KuchenTyp getKuchenTyp() {
        return KuchenTyp.KREMKUCHEN;
    }

    @Override
    protected void fillCommonFields(CakeDTO dto) {
        super.fillCommonFields(dto);
        dto.setExtras(new ArrayList<>(Arrays.asList(this.kremsorte)));
    }
}
