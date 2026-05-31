package dev.kesares.zea.cmd;

import dev.kesares.zea.exception.DuplicateCommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code CommandRegistry} class maintains a registry of commands
 * that can be executed within a shell environment. It provides methods
 * to register, initialize, and retrieve commands by their names.
 * <p>
 * The registry enforces unique command names and prevents duplicate
 * registration of commands.
 */
public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        this.register(new ExitCommand());
        this.register(new EchoCommand());
        this.register(new PwdCommand());
        this.register(new CdCommand());
    }

    /**
     * Registers a new command with the command registry.
     * If a command with the same name is already registered,
     * a {@link DuplicateCommandException} is thrown.
     *
     * @param command the command to be registered. Must implement the {@link Command} interface.
     *                The {@code command.getName()} method must return a unique name
     *                that does not conflict with previously registered commands.
     * @throws DuplicateCommandException if a command with the same name is already registered.
     */
    public void register(Command command) {
        if (this.commands.containsKey(command.getName()))
            throw new DuplicateCommandException("Command already registered: " + command.getName());
        this.commands.put(command.getName(), command);
    }

    /**
     * Finds a command by its name in the command registry.
     * If the command exists, it returns an {@code Optional} containing it;
     * otherwise, it returns an empty {@code Optional}.
     *
     * @param name the name of the command to find. Must not be null.
     * @return an {@code Optional} containing the command if found, or an empty {@code Optional} if no command with the specified name exists.
     */
    public Optional<Command> find(String name) {
        return Optional.ofNullable(this.commands.get(name));
    }
}
