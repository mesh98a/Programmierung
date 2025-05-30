package simulation1;

import domainpackage.AbstractCake;
import domainpackage.KremkuchenImpl;
import domainpackage.ObstkuchenImpl;
import kuchen.Allergen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomCake {
    //private static Random random = new Random();

    public static AbstractCake createRandomCake(Hersteller hersteller,Random random) {
        Set<Allergen> allergene = getRandomAllergens(random);
        int naehrwert = 200 + random.nextInt(301); // 200–500 kcal
        Duration haltbarkeit = Duration.ofHours(24 + random.nextInt(72)); // 1–4 Tage
        BigDecimal preis = BigDecimal.valueOf(5 + random.nextDouble() * 10); // 5–15 Euro

        int type = random.nextInt(2); // 0 = OBSTKUCHEN, 1 = KREMKUCHEN

        if (type == 0) {
            String[] obstsorten = {"Apfel", "Kirsche", "Pfirsich", "Erdbeere"};
            String obstsorte = obstsorten[random.nextInt(obstsorten.length)];

            return new ObstkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, obstsorte);
        } else {
            String[] cremarten = {"Vanille", "Schoko", "Nougat", "Karamell"};
            String kremArt = cremarten[random.nextInt(cremarten.length)];

            return new KremkuchenImpl(hersteller, allergene, naehrwert, haltbarkeit, preis, kremArt);
        }
    }

    private static Set<Allergen> getRandomAllergens(Random random) {
        Set<Allergen> allergens = new HashSet<>();
        Allergen[] allAllergens = Allergen.values();
        int count = random.nextInt(allAllergens.length + 1);

        for (int i = 0; i < count; i++) {
            allergens.add(allAllergens[random.nextInt(allAllergens.length)]);
        }

        return allergens;
    }
    }

