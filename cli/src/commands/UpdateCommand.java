package commands;

import cli.Mode;
import eventsimpl.automatevent.InspectCakeEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.UpdateParser;

import java.util.Map;
import java.util.Scanner;

public class UpdateCommand implements Command {
    @Override
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        while (true) {
            System.out.println("Fachnummer des Kuchens (:x modus verlassen): ");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase(":x")) {
                System.out.println("Update Modus verlassen.");
                break;
            }
            UpdateParser parser = new UpdateParser();
            if (parser.parse(command)) {
                AutomatEvent event = new InspectCakeEvent(parser.getFachnummer());
                AutomatEventHandler handler = handlers.get(Mode.UPDATE_INSPECTDATE);
                if (handler != null) handler.handle(event);
            }
        }
    }
}
