package parser;

import domainpackage.KuchenTyp;

public class ReadCakeParser {
    public KuchenTyp filterTyp;

    public ReadCakeParser(String filterTyp) {
        try {
            this.filterTyp = KuchenTyp.valueOf(filterTyp.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ungültiger KuchenTyp: " + filterTyp + " Anzeige erfolgt ohne Filter.");
            this.filterTyp = null;
        }
    }


}
