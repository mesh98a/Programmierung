package simulation2;

import domainpackage.AbstractCake;
import domainpackage.Automat;

import java.util.List;
import java.util.Random;

public class RemoverTask extends Thread {
    private final Automat automat;
    private final Random random;

    public RemoverTask(Automat automat,Random random) {
        this.automat = automat;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (automat) {
                List<AbstractCake> cakeList = automat.displayListCake();
                if (!cakeList.isEmpty()) {
                    AbstractCake cake = cakeList.get(random.nextInt(cakeList.size()));
                    System.out.println(getName() + ": Versuche Kuchen mit Fachnummer " + cake.getFachnummer() + " zu löschen...");
                    boolean success = automat.deleteCake(cake.getFachnummer());
                }
            }
        }
    }
}
