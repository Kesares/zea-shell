package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.cmd.Command;
import dev.kesares.zea.cmd.CommandRegistry;

import java.util.Optional;

public class CommandExecutorDispatcher {

    private final CommandRegistry commandRegistry;
    private final ExternalCommandExecutor externalCommandExecutor = new ExternalCommandExecutor();
    private final InternalCommandExecuter internalCommandExecuter = new InternalCommandExecuter();

    public CommandExecutorDispatcher(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public CommandExecutor resolveCommandExecutor(String name) {
        Optional<Command> optCommand = this.commandRegistry.find(name);
        if (optCommand.isPresent()) {
            this.internalCommandExecuter.setCommand(optCommand.get());
            return this.internalCommandExecuter;
        }
        return this.externalCommandExecutor;
    }
}
