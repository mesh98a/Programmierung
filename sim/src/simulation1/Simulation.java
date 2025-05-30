package simulation1;

import domainpackage.AbstractCake;
import domainpackage.Automat;
import domainpackage.HerstellerImpl;
import verwaltung.Hersteller;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Simulation {

    public static void main(String[] args) {
        int capacity = 5;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte Kapazität eingeben (oder Enter für Standard 5): ");

        String input = scanner.nextLine();

        if (!input.isBlank()) {
            try {
                capacity = Integer.parseInt(input);
                if (capacity < 0) {
                    System.out.println("Kapazität darf nicht negativ sein. Verwende Standardwert 5.");
                    capacity = 5;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Zahl! Verwende Standardwert 5.");
                capacity = 5;
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

class Inserter extends Thread {
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

class Remover extends Thread {
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

