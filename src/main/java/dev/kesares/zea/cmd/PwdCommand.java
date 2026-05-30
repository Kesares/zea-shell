package dev.kesares.zea.cmd;

import dev.kesares.zea.shell.ShellContext;

public class PwdCommand implements Command {

    @Override
    public String getName() {
        return "pwd";
    }

    @Override
    public void execute(ShellContext context, String... args) {
        System.out.println(context.getCurrentDirectory());
    }
}
