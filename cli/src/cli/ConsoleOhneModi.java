package cli;

import events.eventsimpl.automatevent.*;
import events.eventsystem.automatsystem.AutomatEvent;
import events.eventsystem.automatsystem.AutomatEventHandler;
import parser.*;

import java.util.*;


public class ConsoleOhneModi {
    private final Map<Mode, AutomatEventHandler> handlers = new EnumMap<>(Mode.class);

    public void setHandler(Mode mode, AutomatEventHandler handler) {
        handlers.put(mode, handler);
    }


    public void execute() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (:c = insert, :r = read, :d = delete, :u = update,:x = Programm beenden)");
            String line = scanner.nextLine();
            String[] args = line.split(" ");

            switch (args[0]) {
                case ":c":
                    handleInsert(args);
                    break;
                case ":r":
                    handleRead(args);
                    break;
                case ":d":
                    handleDelete(args);
                    break;
                case ":u":
                    handleUpdate(args);
                    break;
                case ":x":
                    System.out.println("Programm beendet.");
                    System.exit(0);
                default:
                    System.out.println("Unbekannter Befehl: " + args[0]);
            }
        }
    }

    private void handleInsert(String[] args) {
        // Hersteller einfügen
        String herstellerName = "Sonne";
        AutomatEvent hevent = new InsertHerstellerEvent(herstellerName);
        AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
        if (null != herstellerHandler) herstellerHandler.handle(hevent);

        // Kuchen einfügen
        String[] cakeArgs = Arrays.copyOfRange(args, 1, args.length);
        String joined = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        InsertCakeParser k = new InsertCakeParser(joined);
        AutomatEvent kevent = new InsertCakeEvent(k.cake);
        AutomatEventHandler insertCakeHandler = handlers.get(Mode.INSERT_CAKE);
        if (null != insertCakeHandler) insertCakeHandler.handle(kevent);
    }

    private void handleRead(String[] args) {
        ReadCakeParser typ = new ReadCakeParser(args[1]);
        AutomatEvent rkevent = new DisplayCakeEvent(typ.filterTyp);
        AutomatEventHandler displayCakeHandler = handlers.get(Mode.DISPLAY_CAKE);
        if (null != displayCakeHandler) displayCakeHandler.handle(rkevent);
    }

    private void handleDelete(String[] args) {

        DeleteCakeParser d = new DeleteCakeParser(args[1]);
        AutomatEvent devent = new DeleteCakeEvent(d.fachnummer);
        AutomatEventHandler deleteCakeHandler = handlers.get(Mode.DELETE_CAKE);
        if (null != deleteCakeHandler) deleteCakeHandler.handle(devent);
    }

    private void handleUpdate(String[] args) {

        String[] cakeArgs = Arrays.copyOfRange(args, 1, args.length);
        String joined = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        UpdateParser u = new UpdateParser(joined);
        AutomatEvent uevent = new InspectCakeEvent(u.fachnummer, u.inspectdate);
        AutomatEventHandler inspectCakeHandler = handlers.get(Mode.UPDATE_INSPECTDATE);
        if (null != inspectCakeHandler) inspectCakeHandler.handle(uevent);
    }
}
