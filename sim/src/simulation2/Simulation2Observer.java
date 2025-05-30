package simulation2;

import domainpackage.Aktion;
import domainpackage.Automat;
import observerpattern.Beobachter;

public class Simulation2Observer implements Beobachter {
    private Automat automat;

    public Simulation2Observer(Automat automat) {
        this.automat = automat;
        automat.registerObserver(this);
    }

    @Override
    public void update() {
            Aktion aktion = automat.getLetzteAktion();

            Thread thread = automat.getLetzterThread();

            System.out.print(thread.getName());
            switch (aktion) {
                case EINFUEGEN:
                    System.out.println(" Beobachter: Kuchen eingefügt");
                    break;
                case LOESCHEN:
                    System.out.println(" Beobachter: Kuchen gelöscht");
                    break;
            }
    }
}
