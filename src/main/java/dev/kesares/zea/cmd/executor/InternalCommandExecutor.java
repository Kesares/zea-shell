package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.cmd.Command;
import dev.kesares.zea.shell.ShellContext;

public class InternalCommandExecutor implements CommandExecutor {

    private final Command command;

    public InternalCommandExecutor(Command command) {
        this.command = command;
    }

    @Override
    public int execute(ShellContext context, String command, String... args) {
        return this.command.execute(context, args);
    }
}
