package parser;

import kuchen.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class InsertCakeParser {
    private String kuchentyp;
    private String herstellerName;
    private BigDecimal preis;
    private int naehrwert;
    private Duration haltbarkeit;
    private Collection<Allergen> allergene;
    private List<String> kuchensorten;

    public boolean parse(String[] parts) {

        try {
            kuchentyp = parts[0].trim();
            herstellerName = parts[1].trim();
            preis = new BigDecimal(parts[2].trim());
            naehrwert = Integer.parseInt(parts[3].trim());
            int haltbarkeitStunden = Integer.parseInt(parts[4].trim());
            haltbarkeit = Duration.ofHours(haltbarkeitStunden);

            allergene = new HashSet<>();
            String allergenString = parts[5].trim();
            if (!allergenString.isEmpty()) {
                String[] allergenParts = allergenString.split(",");
                for (String a : allergenParts) {
                    allergene.add(Allergen.valueOf(a.trim()));
                }
            }

            kuchensorten = new ArrayList<>();
            kuchensorten.add(parts[6].trim());
            if (parts.length > 7) kuchensorten.add(parts[7].trim());

            return true;
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen: " + e.getMessage());
            return false;
        }
    }


    public String getKuchentyp() {
        return kuchentyp;
    }

    public String getHerstellerName() {
        return herstellerName;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public int getNaehrwert() {
        return naehrwert;
    }

    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    public List<String> getKuchensorten() {
        return kuchensorten;
    }


}

