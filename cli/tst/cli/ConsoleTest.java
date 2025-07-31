package cli;

import commands.Command;
import commands.InsertCommand;
import commands.ReadCommand;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void getInsertCommand() {
        Console console = new Console();
        Command command = console.getCommandMap().get(":c");
        assertEquals(InsertCommand.class, command.getClass());
    }

    @Test
    void getReadCommand() {
        Console console = new Console();
        Command command = console.getCommandMap().get(":r");
        assertEquals(ReadCommand.class, command.getClass());
    }


}