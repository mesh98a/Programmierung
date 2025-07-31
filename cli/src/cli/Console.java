package cli;

import commands.*;
import eventsimpl.automatevent.Mode;
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
        commandMap.put(":p", new PersistenceCommand());
        commandMap.put(":x", new ExitCommand());
    }

    public void setHandler(Mode mode, AutomatEventHandler handler) {
        handlers.put(mode, handler);
    }

    public void execute() {
        Scanner s = new Scanner(System.in);
        this.scanner = s;
        System.out.println("Enter command (:c = insert, :r = read, :d = delete, :u = update,:p = persistence, :x = exit): ");
        String input = s.nextLine().trim();
        while (true) {
            Command command = commandMap.get(input);
            if (command != null) {
                input = command.execute(scanner, handlers, commandMap);
            } else {
                System.out.println("Ungültiger Befehl: " + input);
                input = s.nextLine().trim();
            }
        }

    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

}
