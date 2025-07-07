package simulation3;
import domainpackage.Automat;
import kuchen.Kuchenprodukt;

import java.util.List;
import java.util.Random;

public class UpdaterTask extends Thread {
    private final Automat automat;
    private final Random random;

    public UpdaterTask(Automat automat,Random random) {
        this.automat = automat;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Kuchenprodukt toInspect = null;
                synchronized (automat) {
                    List<Kuchenprodukt> cakeList = automat.getListCake();
                    if (!cakeList.isEmpty()) {
                        toInspect = cakeList.get(random.nextInt(cakeList.size()));
                    }
                }

                if (toInspect != null) {
                    synchronized (automat) {
                        automat.inspectCake(toInspect.getFachnummer());
                    }
                    System.out.println(getName() + ": Kuchen Nr. " + toInspect.getFachnummer() + " inspiziert.");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

