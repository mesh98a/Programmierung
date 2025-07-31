package commands;

import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;

import java.util.Map;
import java.util.Scanner;

public class ExitCommand implements Command{
    @Override
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers,Map<String, Command> commands) {
        System.out.println("Programm beendet.");
        System.exit(0);
        return "";
    }
}
