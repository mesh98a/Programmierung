package simulation2;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import domainpackage.KremkuchenImpl;
import domainpackage.ObstkuchenImpl;
import kuchen.Allergen;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InserterTask extends Thread {
    private final Automat automat;
    private final Hersteller hersteller;
    private Random random;
//    private RandomCake randomCake;

    public InserterTask(Automat automat, Hersteller hersteller, Random random) {
        this.automat = automat;
        this.hersteller = hersteller;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            extracted(hersteller,random);
        }
    }

    public boolean extracted(Hersteller hersteller,Random random) {
        AbstractCake cake = createRandomCake(hersteller, random);
        System.out.println(getName() + ": Versuche Kuchen einzufügen...");
        boolean success = automat.insertCake(cake);
        return success;
    }

    public AbstractCake createRandomCake(Hersteller hersteller,Random random) {
        Set<Allergen> allergene = getRandomAllergens(random);
        int naehrwert = 200 + random.nextInt(301); // 200–500 kcal
        Duration haltbarkeit = Duration.ofHours(24 + random.nextInt(72)); // 1–4 Tage
        BigDecimal preis = BigDecimal.valueOf(5 + random.nextDouble() * 10); // 5–15 Euro

        int type = random.nextInt(2); // 0 = OBSTKUCHEN, 1 = KREMKUCHEN

        if (type == 0) {
            String[] obstsorten = {"Apfel", "Kirsche", "Pfirsich", "Erdbeere"};
            String obstsorte = obstsorten[random.nextInt(obstsorten.length)];

            return new ObstkuchenImpl(hersteller, preis, naehrwert, haltbarkeit, allergene, obstsorte);
        } else {
            String[] kremarten = {"Vanille", "Schoko", "Nougat", "Karamell"};
            String kremArt = kremarten[random.nextInt(kremarten.length)];

            return new KremkuchenImpl(hersteller, preis, naehrwert, haltbarkeit, allergene, kremArt);
        }
    }

    private  Set<Allergen> getRandomAllergens(Random random) {
        Set<Allergen> allergens = new HashSet<>();
        Allergen[] allAllergens = Allergen.values();
        int count = random.nextInt(allAllergens.length + 1);

        for (int i = 0; i < count; i++) {
            allergens.add(allAllergens[random.nextInt(allAllergens.length)]);
        }

        return allergens;
    }

}
