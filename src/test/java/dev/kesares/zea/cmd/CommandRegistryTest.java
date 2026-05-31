package dev.kesares.zea.cmd;

import dev.kesares.zea.exception.DuplicateCommandException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommandRegistryTest {

    private final CommandRegistry commandRegistry = new CommandRegistry();

    @Test
    void findCommands() {
        Optional<Command> c1 = this.commandRegistry.find("echo");
        Optional<Command> c2 = this.commandRegistry.find("pwd");
        Optional<Command> c3 = this.commandRegistry.find("exit");


        assertTrue(c1.isPresent());
        assertTrue(c2.isPresent());
        assertTrue(c3.isPresent());
    }

    @Test
    void testReturnEmptyOptionalForUnknownCommands() {
        Optional<Command> c1 = this.commandRegistry.find("test");
        Optional<Command> c2 = this.commandRegistry.find("swim");

        assertEquals(Optional.empty(), c1);
        assertEquals(Optional.empty(), c2);
    }

    @Test
    void testThrowDuplicateCommandException() {
        assertThrows(DuplicateCommandException.class, () -> this.commandRegistry.register(new EchoCommand()));
        assertThrows(DuplicateCommandException.class, () -> this.commandRegistry.register(new PwdCommand()));
        assertThrows(DuplicateCommandException.class, () -> this.commandRegistry.register(new ExitCommand()));
    }
}