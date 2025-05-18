package parser;

import domainpackage.*;
import kuchen.Allergen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;

public class InsertCakeParser {
    public final AbstractCake cake;

    public InsertCakeParser(String input) {
        this.cake = parseCakeFromLine(input);
    }

    private AbstractCake parseCakeFromLine(String input) {
        try {
            String[] parts = input.split(" ");

            String type = parts[0];
            String herstellerName = parts[1];
            BigDecimal preis = new BigDecimal(parts[2]);
            int naehrwert = Integer.parseInt(parts[3]);
            int haltbarkeitStunden = Integer.parseInt(parts[4]);
            Duration haltbarkeit = Duration.ofHours(haltbarkeitStunden);

            String allergenString = parts[5];
            Collection<Allergen> allergene = new HashSet<>();
            if (!allergenString.equals(",")) {
                String[] allergenParts = allergenString.split(",");
                for (String a : allergenParts) {
                    allergene.add(Allergen.valueOf(a));
                }
            }
            Hersteller hersteller = new HerstellerImpl(herstellerName);

            if (type.equalsIgnoreCase("Obstkuchen")) {
                String obstsorte = parts[6];
                return new ObstkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, obstsorte);
            } else if (type.equalsIgnoreCase("Kremkuchen")) {
                String kremsorte = parts[6];
                return new KremkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, kremsorte);
            } else {
                System.out.println("Unbekannter Kuchentyp: " + type);
                return null;
            }

        } catch (Exception e) {
            System.out.println("Fehler beim Parsen: " + e.getMessage());
            return null;
        }
    }
}

