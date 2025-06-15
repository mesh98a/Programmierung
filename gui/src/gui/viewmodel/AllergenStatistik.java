package gui.viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import kuchen.Allergen;

public class AllergenStatistik {
    private SimpleObjectProperty<Allergen> allergen;

    public AllergenStatistik(Allergen allergen) {
        this.allergen = new SimpleObjectProperty<Allergen>(allergen);
    }
    public Allergen getAllergen() {
        return allergen.get();
    }
}
