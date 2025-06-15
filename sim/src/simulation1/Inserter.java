package simulation1;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import verwaltung.Hersteller;

import java.util.Random;

public class Inserter extends Thread {
    private Automat automat;
    private Hersteller hersteller;
    private Random random;

    public Inserter(Automat automat, Hersteller hersteller, Random random) {
        this.automat = automat;
        this.hersteller = hersteller;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            AbstractCake cake = RandomCake.createRandomCake(hersteller,random);
            boolean success = automat.insertCake(cake);
            System.out.println(Thread.currentThread().getName() +
                    " → Kuchen eingefügt: " + success +
                    ", freie Plätze: " + automat.getFreeCapacity());
        }
    }
}
