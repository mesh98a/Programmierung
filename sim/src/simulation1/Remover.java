package simulation1;

import domainpackage.Automat;
import kuchen.Kuchenprodukt;

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
            extracted();
        }

    }

    private void extracted() {
        synchronized (automat) {
            List<Kuchenprodukt> cakeList = automat.getListCake();
            if (!cakeList.isEmpty()) {
                Kuchenprodukt cake = cakeList.get(random.nextInt(cakeList.size()));
                boolean success = automat.deleteCake(cake.getFachnummer());
                System.out.println(Thread.currentThread().getName() +
                        " → Kuchen gelöscht: " + success +
                        " (Fachnummer " + cake.getFachnummer() + ")");
            }
        }
    }
}

