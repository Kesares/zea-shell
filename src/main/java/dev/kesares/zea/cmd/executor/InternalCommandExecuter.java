package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.cmd.Command;
import dev.kesares.zea.shell.ShellContext;

public class InternalCommandExecuter implements CommandExecutor {

    private Command command;

    @Override
    public void execute(ShellContext context, String... args) {
        this.command.execute(context, args);
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
