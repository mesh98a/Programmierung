package commands;

import cli.Console;
import eventsimpl.automatevent.Mode;
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
                AutomatEvent hevent = new InsertHerstellerEvent(new Console(),parts[0]);
                AutomatEventHandler herstellerHandler = handlers.get(Mode.INSERT_HERSTELLER);
                if (herstellerHandler != null) herstellerHandler.handle(hevent);

            } else if (parts.length == 7 || parts.length == 8) {
                InsertCakeParser k = new InsertCakeParser();
                if (k.parse(parts)) {
                    AutomatEvent kevent = new InsertCakeEvent(
                            new Console(),
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

