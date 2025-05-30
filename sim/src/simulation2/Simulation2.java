package simulation2;

import domainpackage.*;
import observerpattern.Beobachter;
import verwaltung.Hersteller;
import java.util.*;

public class Simulation2 {
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

        int n = 1;
        System.out.print("Anzahl der Einfüge-/Lösch-Threads eingeben (Standard 1): ");
        input = scanner.nextLine();
        if (!input.isBlank()) {
            try {
                n = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Zahl! Verwende Standardwert 1.");
            }
        }

        Automat automat = new Automat(capacity);
        Beobachter observer = new Simulation2Observer(automat);

        Hersteller hersteller = new HerstellerImpl("Bäcker");
        automat.insertHersteller("Bäcker");
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            new InserterTask(automat, hersteller, random).start();
            new RemoverTask(automat,random).start();
        }

    }
}