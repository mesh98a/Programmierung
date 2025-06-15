package commands;

import cli.Mode;
import eventsystem.automatsystem.AutomatEventHandler;

import java.util.Map;
import java.util.Scanner;

public class ExitCommand implements Command{
    @Override
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        System.out.println("Programm beendet.");
        System.exit(0);
    }
}
