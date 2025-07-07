package commands;

import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;

import java.util.Map;
import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers);
}
