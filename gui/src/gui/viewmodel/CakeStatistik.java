package gui.viewmodel;

import javafx.beans.property.*;

import java.util.Date;

public class CakeStatistik {
    private SimpleIntegerProperty fachnummer;
    private SimpleStringProperty hersteller;
    private SimpleLongProperty haltbarkeit;
    private SimpleObjectProperty<Date> inspektionsdatum;

    public CakeStatistik(int fachnummer,String hersteller, Date inspektionsdatum , long haltbarkeit) {
        this.fachnummer = new SimpleIntegerProperty(fachnummer);
        this.hersteller = new SimpleStringProperty(hersteller);
        this.inspektionsdatum = new SimpleObjectProperty<>(inspektionsdatum); ;
        this.haltbarkeit = new SimpleLongProperty(haltbarkeit);
    }

    public Integer getFachnummer() {
        return fachnummer.get();
    }

    public String getHersteller() {
        return hersteller.get();
    }

    public Date getInspektionsdatum() {
        return inspektionsdatum.get();
    }

    public long getHaltbarkeit() {
        return haltbarkeit.get();
    }
}
