package cli;

import events.eventsimpl.automatevent.*;
import events.eventsystem.automatsystem.AutomatEvent;
import events.eventsystem.automatsystem.AutomatEventHandler;
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
                        AutomatEvent uevent = new InspectCakeEvent(u.fachnummer, u.inspectdate);
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
        System.out.println("Was möchtest du einfügen? (:h hersteller / :k kuchen)");
        String command = scanner.nextLine().trim();

        switch (command) {
            case ":h":
                System.out.println("Herstellername:");
                InsertHerstellerParser h = new InsertHerstellerParser(scanner.nextLine());
                AutomatEvent hevent = new InsertHerstellerEvent( h.herstellerName);
                AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
                if (null != herstellerHandler) herstellerHandler.handle(hevent);
                break;
            case ":k":
                System.out.println("Kuchen:");
                InsertCakeParser k = new InsertCakeParser(scanner.nextLine());
                AutomatEvent kevent = new InsertCakeEvent(k.cake);
                AutomatEventHandler insertCakeHandler = handlers.get(Mode.INSERT_CAKE);
                if (null != insertCakeHandler) insertCakeHandler.handle(kevent);
                break;
            default:
                System.out.println("Unbekannter Einfügebefehl.");
        }
    }
    private void handleRead(){
        System.out.println("Was möchtest du ansehen? (:h hersteller / :k kuchen)");
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
            default:
                System.out.println("Unbekannter Einfügebefehl.");
        }

    }





}
