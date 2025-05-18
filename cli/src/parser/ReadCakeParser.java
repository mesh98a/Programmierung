package parser;

import domainpackage.KuchenTyp;

public class ReadCakeParser {
    public final KuchenTyp filterTyp;

    public ReadCakeParser(String filterTyp) {
        this.filterTyp = KuchenTyp.valueOf(filterTyp.toUpperCase());
    }


}
