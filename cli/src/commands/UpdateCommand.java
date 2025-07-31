package commands;

import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.InspectCakeEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.UpdateParser;

import java.util.Map;
import java.util.Scanner;

public class UpdateCommand implements Command {
    @Override
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers,Map<String, Command> commands) {
        while (true) {
            System.out.println("Fachnummer des Kuchens");
            String command = scanner.nextLine().trim();
            if (command.startsWith(":") && commands.containsKey(command)) {
                System.out.println("Moduswechsel auf " + command);
                return command;
            }
                UpdateParser parser = new UpdateParser();
            if (parser.parse(command)) {
                AutomatEvent event = new InspectCakeEvent(this,parser.getFachnummer());
                AutomatEventHandler handler = handlers.get(Mode.UPDATE_INSPECTDATE);
                if (handler != null) handler.handle(event);
            }
        }
    }
}
