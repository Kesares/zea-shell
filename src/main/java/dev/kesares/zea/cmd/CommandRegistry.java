package dev.kesares.zea.cmd;

import dev.kesares.zea.exception.CommandAlreadyRegisteredException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public void init() {
        this.register(new EchoCommand());
        this.register(new PwdCommand());
    }

    public void register(Command command) {
        if (this.commands.containsKey(command.getName()))
            throw new CommandAlreadyRegisteredException("Command already registered: " + command.getName());
        this.commands.put(command.getName(), command);
    }

    public Optional<Command> find(String name) {
        return Optional.ofNullable(this.commands.get(name));
    }
}
