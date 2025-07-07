package parser;


import kuchen.KuchenTyp;

public class ReadCakeParser {
    private KuchenTyp filterTyp;

    public boolean parse(String input) {
        try {
            this.filterTyp = KuchenTyp.valueOf(input);
            return true;
        } catch (Exception e) {
            System.out.println("Kein gültiger Kuchentyp");
            return false;
        }
    }

    public KuchenTyp getFilterTyp() {
        return filterTyp;
    }
}
