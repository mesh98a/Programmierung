package observers;

import domainpackage.Automat;
import observerpattern.Beobachter;

public class Simulation2Observer implements Beobachter {
    private Automat automat;
    int lastSize;

    public Simulation2Observer(Automat automat) {
        this.automat = automat;
        this.lastSize = 0;
        automat.registerObserver(this);
    }

    @Override
    public void update() {
        int cakeSize = automat.getListCake().size();
        if (cakeSize > lastSize) {
            System.out.println(" Beobachter: Kuchen eingefügt");
            lastSize = cakeSize;
        } else if (cakeSize < lastSize) {
            System.out.println(" Beobachter: Kuchen gelöscht");
            lastSize = cakeSize;
        }

    }
}
