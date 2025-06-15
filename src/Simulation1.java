import domainpackage.Automat;
import domainpackage.HerstellerImpl;
import simulation1.Inserter;
import simulation1.Remover;
import verwaltung.Hersteller;

import java.util.Random;
import java.util.Scanner;

public class Simulation1 {
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
