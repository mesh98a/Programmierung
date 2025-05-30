package cli;

import eventsimpl.automatevent.*;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private final Map<Mode, AutomatEventHandler> handlers = new EnumMap<>(Mode.class);

    public void setHandler(Mode mode, AutomatEventHandler handler) {
        handlers.put(mode, handler);
    }

    public void execute() {
        try (Scanner s = new Scanner(System.in)) {
            this.scanner = s;
            while (true) {
                System.out.println("Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit): ");
                String input = s.nextLine();
                switch (input) {
                    case ":c":
                        handleInsert();
                        break;
                    case ":r":
                        handleRead();
                        break;
                    case ":d":
                        System.out.println("Fachnummer des Kuchens :");
                        DeleteCakeParser d = new DeleteCakeParser(scanner.nextLine());
                        AutomatEvent devent = new DeleteCakeEvent(d.fachnummer);
                        AutomatEventHandler deleteCakeHandler = handlers.get(Mode.DELETE_CAKE);
                        if (null != deleteCakeHandler) deleteCakeHandler.handle(devent);
                        break;
                    case ":u":
                        System.out.println("Fachnummer des Kuchens und das Datum im yyyy-mm-dd Format:");
                        UpdateParser u = new UpdateParser(scanner.nextLine());
                        AutomatEvent uevent = new InspectCakeEvent(u.fachnummer);
                        AutomatEventHandler inspectCakeHandler = handlers.get(Mode.UPDATE_INSPECTDATE);
                        if (null != inspectCakeHandler) inspectCakeHandler.handle(uevent);
                        break;
                    case ":x":
                        System.out.println("Programm beendet.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid command");
                }
            }
        }
    }

    private void handleInsert() {
        System.out.println("Hersteller oder Kuchen einfügen?");
        String command = scanner.nextLine().trim();
        String[] parts = command.split(" ");

        if (parts.length == 1) {
            InsertHerstellerParser h = new InsertHerstellerParser(parts[0]);
            AutomatEvent hevent = new InsertHerstellerEvent(h.herstellerName);
            AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
            if (null != herstellerHandler) herstellerHandler.handle(hevent);
        } else if (parts.length == 6 || parts.length == 7) {
            InsertCakeParser k = new InsertCakeParser(parts);
            AutomatEvent kevent = new InsertCakeEvent(k.cake);
            AutomatEventHandler insertCakeHandler = handlers.get(Mode.INSERT_CAKE);
            if (null != insertCakeHandler) insertCakeHandler.handle(kevent);
        } else {
            System.out.println("Fehler: Ungültige Anzahl an Tokens (" + parts.length + ")");
        }

    }

    private void handleRead() {
        System.out.println("Was möchtest du ansehen? (:h hersteller / :k kuchen / :a  allergen)");
        String command = scanner.nextLine().trim();

        switch (command) {
            case ":h":
                AutomatEvent rhevent = new GetHerstellerMapEvent();
                AutomatEventHandler displayHerstellerHandler = handlers.get(Mode.DISPLAY_HERSTELLER);
                if (null != displayHerstellerHandler) displayHerstellerHandler.handle(rhevent);
                break;

            case ":k":
                System.out.println("Filtertyp:");
                ReadCakeParser typ = new ReadCakeParser(scanner.nextLine());
                AutomatEvent rkevent = new DisplayCakeEvent(typ.filterTyp);
                AutomatEventHandler displayCakeHandler = handlers.get(Mode.DISPLAY_CAKE);
                if (null != displayCakeHandler) displayCakeHandler.handle(rkevent);
                break;
            case ":a":
                System.out.println("enthalten(i)/nicht enthalten(e): ");
                String vorhanden = scanner.nextLine();

                AutomatEvent raevent;
                if ("i".equalsIgnoreCase(vorhanden)) {
                    raevent = new DisplayAllergenEvent();
                    AutomatEventHandler displayAllergenHandler = handlers.get(Mode.DISPLAY_ALLERGEN);
                    if (displayAllergenHandler != null) {
                        displayAllergenHandler.handle(raevent);
                    }
                } else if ("e".equalsIgnoreCase(vorhanden)) {
                    raevent = new DisplayKeineAllergenEvent();
                    AutomatEventHandler displayAllergenHandler = handlers.get(Mode.DISPLAY_KEINE_ALLERGEN);
                    if (displayAllergenHandler != null) {
                        displayAllergenHandler.handle(raevent);
                    }
                } else {
                    System.out.println("Ungültige Eingabe. Bitte 'i' oder 'e' eingeben.");
                    break;
                }
                break;
            default:
                System.out.println("Unbekannter Einfügebefehl.");
        }

    }


}
