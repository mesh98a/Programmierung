package commands;

import cli.Console;
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
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        while (true) {
            System.out.print("(:s JOS|JBP speichere die Datei / :l JOS|JBP lade die Datei / :x Modus verlassen) ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(":x")) {
                System.out.println("Persistence Modus verlassen.");
                break;
            }

            PersistenceParser parser = new PersistenceParser();
            if (parser.parse(command)) {

                switch (parser.getAction()) {
                    case ":s":
                        AutomatEvent persistenceSaveEvent = new PersistenceSaveEvent(new Console(),parser.getMode());
                        AutomatEventHandler persistenceSaveHandler = handlers.get(Mode.PERSIST_SAVE);
                        if (persistenceSaveHandler != null) persistenceSaveHandler.handle(persistenceSaveEvent);
                        break;

                    case ":l":
                        AutomatEvent persistenceLoadEvent = new PersistenceLoadEvent(new Console(),parser.getMode());
                        AutomatEventHandler persistenceLoadHandler = handlers.get(Mode.PERSIST_LOAD);
                        if (persistenceLoadHandler != null) persistenceLoadHandler.handle(persistenceLoadEvent);
                        break;
                    default:
                        System.out.println("Unbekannter Lesebefehl.");
                }
            }
        }
    }
}
