package commands;

import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;

import java.util.Map;
import java.util.Scanner;

public interface Command {
   String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers,Map<String, Command> commands);
}
