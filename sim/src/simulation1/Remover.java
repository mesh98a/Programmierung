package simulation1;

import domainpackage.AbstractCake;
import domainpackage.Automat;

import java.util.List;
import java.util.Random;

public class Remover extends Thread {
    private Automat automat;
    private Random random = new Random();

    public Remover(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (automat) {
                List<AbstractCake> cakeList = automat.displayListCake();
                if (!cakeList.isEmpty()) {
                    AbstractCake cake = cakeList.get(random.nextInt(cakeList.size()));
                    boolean success = automat.deleteCake(cake.getFachnummer());
                    System.out.println(Thread.currentThread().getName() +
                            " → Kuchen gelöscht: " + success +
                            " (Fachnummer " + cake.getFachnummer() + ")");
                }
            }
        }

    }
}

