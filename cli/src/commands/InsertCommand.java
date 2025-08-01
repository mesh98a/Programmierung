package commands;

import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.InsertCakeEvent;
import eventsimpl.automatevent.InsertHerstellerEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.InsertCakeParser;

import java.util.*;

public class InsertCommand implements Command {
    @Override
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers, Map<String, Command> commands) {
        while (true) {
            System.out.println("Hersteller oder Kuchen einfügen");
            String command = scanner.nextLine().trim();

            if (command.startsWith(":") && commands.containsKey(command)) {
                System.out.println("Moduswechsel auf " + command);
                return command;
            }
            String[] parts = command.split(" ");

            if (parts.length == 1) {
                insertHersteller(parts, handlers);

            } else if (parts.length == 7 || parts.length == 8) {
                insertCake(parts, handlers);

            } else {
                System.out.println("Fehler: Ungültige Anzahl an Tokens (" + parts.length + ")");
            }
        }
    }

    public void insertHersteller(String[] parts, Map<Mode, AutomatEventHandler> handlers) {
        AutomatEvent hevent = new InsertHerstellerEvent(this, parts[0]);
        AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
        if (herstellerHandler != null) herstellerHandler.handle(hevent);
    }

    public void insertCake(String[] parts, Map<Mode, AutomatEventHandler> handlers) {
        InsertCakeParser k = new InsertCakeParser();
        if (k.parse(parts)) {
            AutomatEvent kevent = new InsertCakeEvent(
                    this,
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
    }

}

