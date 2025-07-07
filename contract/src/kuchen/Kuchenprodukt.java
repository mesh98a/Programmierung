package kuchen;

import verwaltung.Verkaufsobjekt;

import java.time.LocalDateTime;

public interface Kuchenprodukt extends Kuchen, Verkaufsobjekt {
    LocalDateTime getEinfuegedatum();
    KuchenTyp getKuchenTyp();
}
