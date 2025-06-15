package cli;

import commands.*;
import eventsystem.automatsystem.AutomatEventHandler;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private final Map<Mode, AutomatEventHandler> handlers = new EnumMap<>(Mode.class);
    private final Map<String, Command> commandMap = new HashMap<>();

    public Console() {
        commandMap.put(":c", new InsertCommand());
        commandMap.put(":r", new ReadCommand());
        commandMap.put(":d", new DeleteCommand());
        commandMap.put(":u", new UpdateCommand());
        commandMap.put(":x", new ExitCommand());
    }

    public void setHandler(Mode mode, AutomatEventHandler handler) {
        handlers.put(mode, handler);
    }

    public void execute() {
        Scanner s = new Scanner(System.in);
        this.scanner = s;
            while (true) {
                System.out.println("Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit): ");
                String input = s.nextLine().trim();
                Command command = commandMap.get(input);
                if (command != null) {
                    command.execute(scanner, handlers);
                } else {
                    System.out.println("Ungültiger Befehl: " + input);
                }
            }

    }
}
