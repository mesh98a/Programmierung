import domainpackage.*;
import observerpattern.Beobachter;
import simulation2.InserterTask;
import simulation2.RemoverTask;
import observers.Simulation2Observer;
import verwaltung.Hersteller;
import java.util.*;

public class Simulation2 {
    private static int capacity = 5;
    private static int threads = 2;
    public static void main(String[] args) {
        int capacity = 5;

        if (args.length > 0) {
            try {
                capacity = Integer.parseInt(args[0]);
                threads = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        Automat automat = new Automat(capacity);
        Beobachter observer = new Simulation2Observer(automat);

        Hersteller hersteller = new HerstellerImpl("Bäcker");
        automat.insertHersteller("Bäcker");
        Random random = new Random();

        for (int i = 0; i < threads; i++) {
            new InserterTask(automat, hersteller, random).start();
            new RemoverTask(automat,random).start();
        }

    }
}