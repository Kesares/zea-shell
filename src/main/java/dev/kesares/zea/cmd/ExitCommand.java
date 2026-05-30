package dev.kesares.zea.cmd;

import dev.kesares.zea.shell.ShellContext;

public class ExitCommand implements Command {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void execute(ShellContext context, String... args) {
        context.stop();
        context.setLastExitCode(0);
    }
}
