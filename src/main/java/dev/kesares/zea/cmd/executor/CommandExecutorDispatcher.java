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
    private final ExternalCommandExecutor externalCommandExecutor = new ExternalCommandExecutor();
    private final InternalCommandExecutor internalCommandExecutor = new InternalCommandExecutor();

    public CommandExecutorDispatcher(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    /**
     * Resolves the appropriate {@link CommandExecutor} based on the provided command name.
     * If a registered command with the specified name is found, it configures and returns
     * the internal command executor. Otherwise, it falls back to the external command executor.
     *
     * @param name the name of the command to execute. Must not be null.
     * @return the resolved {@link CommandExecutor}, either internal or external,
     *         based on the existence of the command in the registry.
     */
    public CommandExecutor resolveCommandExecutor(String name) {
        Optional<Command> optCommand = this.commandRegistry.find(name);
        if (optCommand.isPresent()) {
            this.internalCommandExecutor.setCommand(optCommand.get());
            return this.internalCommandExecutor;
        }
        return this.externalCommandExecutor;
    }
}
