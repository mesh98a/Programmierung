package commands;

import cli.Mode;
import eventsimpl.automatevent.InsertCakeEvent;
import eventsimpl.automatevent.InsertHerstellerEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.InsertCakeParser;

import java.util.*;

public class InsertCommand implements Command {
    @Override
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        while (true) {
            System.out.println("Hersteller oder Kuchen einfügen (:x modus verlassen): ");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase(":x")) {
                System.out.println("Insert Modus verlassen.");
                break;
            }
            String[] parts = command.split(" ");

            if (parts.length == 1) {
                AutomatEvent hevent = new InsertHerstellerEvent(parts[0]);
                AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
                if (herstellerHandler != null) herstellerHandler.handle(hevent);
            } else if (parts.length == 7 || parts.length == 8) {
                InsertCakeParser k = new InsertCakeParser();
                if (k.parse(parts)) {
                    AutomatEvent kevent = new InsertCakeEvent(
                            k.getKuchentyp(),
                            k.getHerstellerName(),
                            k.getPreis(),
                            k.getNaehrwert(),
                            k.getHaltbarkeit(),
                            k.getAllergene(),
                            k.getKuchensorten()
                    );
                    AutomatEventHandler insertCakeHandler = handlers.get(Mode.INSERT_CAKE);
                    if (insertCakeHandler != null) {
                        insertCakeHandler.handle(kevent);
                    }

                }
            } else {
                System.out.println("Fehler: Ungültige Anzahl an Tokens (" + parts.length + ")");
            }
        }
    }

}


//class InsertCakeParser {
//    private String kuchentyp;
//    private String herstellerName;
//    private List<String> allergenNamen;
//    private int naehrwert;
//    private Duration haltbarkeit;
//    private BigDecimal preis;
//    private String obstsorte;
//    private String kremsorte;
//
//    public InsertCakeParser(String[] parts) throws IllegalArgumentException {
//            if (parts.length < 6) {
//                throw new IllegalArgumentException("Zu wenige Parameter: mindestens 6 erwartet, aber " + parts.length + " gefunden.");
//            }
//            this.kuchentyp = parts[0];
//            this.herstellerName = parts[1];
//            try {
//                this.preis = new BigDecimal(parts[2].replace(',', '.'));  // falls Komma als Dezimaltrennzeichen
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Ungültiger Preis: " + parts[2]);
//            }
//            try {
//                this.naehrwert = Integer.parseInt(parts[3]);
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Ungültiger Nährwert: " + parts[3]);
//            }
//            try {
//                this.haltbarkeit = Duration.ofHours(Long.parseLong(parts[4]));
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Ungültige Haltbarkeit (Stunden): " + parts[4]);
//            }
//
//            this.allergenNamen = parts[5].isEmpty() ? List.of() : Arrays.stream(parts[5].split(","))
//                    .map(String::trim)
//                    .collect(Collectors.toList());
//
//            switch (kuchentyp.toLowerCase()) {
//                case "obstkuchen":
//                    if (parts.length < 7) throw new IllegalArgumentException("Obstkuchen erwartet Obstsorte als 7. Parameter");
//                    this.obstsorte = parts[6];
//                    break;
//                case "kremkuchen":
//                    if (parts.length < 7) throw new IllegalArgumentException("Kremkuchen erwartet Kremsorte als 7. Parameter");
//                    this.kremsorte = parts[6];
//                    break;
//                case "obsttorte":
//                    if (parts.length < 8) throw new IllegalArgumentException("Obsttorte erwartet Obstsorte und Kremsorte als 7. und 8. Parameter");
//                    this.obstsorte = parts[6];
//                    this.kremsorte = parts[7];
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unbekannter Kuchentyp: " + kuchentyp);
//            }
//        }
//
//    }
