package commands;

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
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers,Map<String, Command> commands) {
        while (true) {
            System.out.println("Herstellername oder Fachnummer eingeben");
            String input = scanner.nextLine().trim();
            if (input.startsWith(":") && commands.containsKey(input)) {
                System.out.println("Moduswechsel auf " + input);
                return input;
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

