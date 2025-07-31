import domainpackage.Automat;
import domainpackage.HerstellerImpl;
import simulation1.Inserter;
import simulation1.Remover;
import verwaltung.Hersteller;

import java.util.Random;
import java.util.Scanner;

public class Simulation1 {
    private static int capacity = 5;
    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                capacity = Integer.parseInt(args[0]);
                System.out.println("Kapazität gesetzt auf: " + capacity);
            } catch (NumberFormatException e) {
                System.out.println("Ungültiges Argument: '" + args[0] + "'. Verwende Standard-Kapazität " + capacity);
            }
        }
        Automat automat = new Automat(capacity);
        Hersteller hersteller = new HerstellerImpl("Bäcker");
        automat.insertHersteller("Bäcker");
        Random random = new Random();

        Inserter inserterThread = new Inserter(automat, hersteller,random);
        Remover removerThread = new Remover(automat);
        inserterThread.start();
        removerThread.start();

    }

}
