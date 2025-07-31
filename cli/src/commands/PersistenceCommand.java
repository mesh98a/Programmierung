package commands;

import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.PersistenceLoadEvent;
import eventsimpl.automatevent.PersistenceSaveEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.PersistenceParser;

import java.util.Map;
import java.util.Scanner;

public class PersistenceCommand implements Command {
    @Override
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers, Map<String, Command> commands) {
        while (true) {
            System.out.print("(save JOS|JBP speichere die Datei / load JOS|JBP lade die Datei / :x Modus verlassen) ");
            String command = scanner.nextLine();

            if (command.startsWith(":") && commands.containsKey(command)) {
                System.out.println("Moduswechsel auf " + command);
                return command;
            }

            PersistenceParser parser = new PersistenceParser();
            if (parser.parse(command)) {

                switch (parser.getAction()) {
                    case "save":
                        save(parser, handlers);
                        break;

                    case "load":
                        load(parser, handlers);
                        break;
                    default:
                        System.out.println("Unbekannter Lesebefehl.");
                }
            }
        }
    }

    public void save(PersistenceParser parser, Map<Mode, AutomatEventHandler> handlers) {
        AutomatEvent persistenceSaveEvent = new PersistenceSaveEvent(this, parser.getMode());
        AutomatEventHandler persistenceSaveHandler = handlers.get(Mode.PERSIST_SAVE);
        if (persistenceSaveHandler != null) persistenceSaveHandler.handle(persistenceSaveEvent);
    }

    public void load(PersistenceParser parser, Map<Mode, AutomatEventHandler> handlers) {
        AutomatEvent persistenceLoadEvent = new PersistenceLoadEvent(this, parser.getMode());
        AutomatEventHandler persistenceLoadHandler = handlers.get(Mode.PERSIST_LOAD);
        if (persistenceLoadHandler != null) persistenceLoadHandler.handle(persistenceLoadEvent);
    }
}
