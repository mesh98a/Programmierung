package simulation2;

import domainpackage.Automat;
import kuchen.Kuchenprodukt;

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
            extracted();
        }
    }

    private void extracted() {
        synchronized (automat) {
            List<Kuchenprodukt> cakeList = automat.getListCake();
            if (!cakeList.isEmpty()) {
                Kuchenprodukt cake = cakeList.get(random.nextInt(cakeList.size()));
                System.out.println(getName() + ": Versuche Kuchen mit Fachnummer " + cake.getFachnummer() + " zu löschen...");
                boolean success = automat.deleteCake(cake.getFachnummer());
            }
        }
    }
}
