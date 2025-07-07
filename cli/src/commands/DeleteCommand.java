package commands;

import cli.Console;
import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.DeleteCakeEvent;
import eventsimpl.automatevent.DeleteHerstellerEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.DeleteCakeParser;

import java.util.Map;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        while (true) {
            System.out.println("Herstellername oder Fachnummer eingeben (:x modus verlassen): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(":x")) {
                System.out.println("Delete Modus verlassen.");
                break;
            }
            if (input.matches("\\d+")) {
                DeleteCakeParser dcparser = new DeleteCakeParser();
                if (dcparser.parse(input)) {
                    AutomatEvent event = new DeleteCakeEvent(this,dcparser.getFachnummer());
                    AutomatEventHandler handler = handlers.get(Mode.DELETE_CAKE);
                    if (handler != null) handler.handle(event);
                }
            } else {
                    AutomatEvent event = new DeleteHerstellerEvent(this,input);
                    AutomatEventHandler handler = handlers.get(Mode.DELETE_HERSTELLER);
                    if (handler != null) handler.handle(event);

            }
        }
    }
}
