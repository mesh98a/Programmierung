package gui.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HerstellerStatistik {
    private StringProperty name;
    private IntegerProperty number_of_cakes;

    public HerstellerStatistik(String name, int number_of_cakes) {
        this.name = new SimpleStringProperty(name);
        this.number_of_cakes = new SimpleIntegerProperty(number_of_cakes);
    }
    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty number_of_cakesProperty() {
        return number_of_cakes;
    }

    public String getName() {
        return name.get();
    }

    public int getNumber_of_cakes() {
        return number_of_cakes.get();
    }

}
