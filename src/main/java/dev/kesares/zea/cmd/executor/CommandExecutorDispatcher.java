package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.cmd.Command;
import dev.kesares.zea.cmd.CommandRegistry;

import java.util.Optional;

/**
 * The {@code CommandExecutorDispatcher} class is responsible for resolving and dispatching
 * command execution to the appropriate executor based on the command's nature.
 * It coordinates between internal and external command execution implementations
 * by determining whether a command should be handled locally via an internal executor
 * or delegated to an external process.
 * <p>
 * This class relies on a {@link CommandRegistry} for resolving known internal commands,
 * and falls back to using an {@link ExternalCommandExecutor} for unrecognized commands.
 */
public class CommandExecutorDispatcher {

    private final CommandRegistry commandRegistry;

    public CommandExecutorDispatcher(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    /**
     * Resolves a {@link CommandExecutor} for the given command name. If the command is found
     * in the internal {@code CommandRegistry}, an {@link InternalCommandExecutor} is returned.
     * Otherwise, an {@link ExternalCommandExecutor} is provided as a fallback to handle unknown
     * or external commands.
     *
     * @param name the name of the command to resolve. Must not be null.
     * @return an implementation of {@link CommandExecutor} capable of executing the specified command.
     */
    public CommandExecutor resolveCommandExecutor(String name) {
        Optional<Command> optCommand = this.commandRegistry.find(name);
        if (optCommand.isPresent())
            return new InternalCommandExecutor(optCommand.get());
        return new ExternalCommandExecutor();
    }
}
