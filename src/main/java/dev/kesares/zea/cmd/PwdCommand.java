package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;

public class PwdCommand implements Command {

    @Override
    public String getName() {
        return "pwd";
    }

    @Override
    public int execute(ShellContext context, String... args) {
        if (args.length > 0) {
            IO.println(this.getName() + ": to many arguments");
            return ExitCode.USAGE_ERROR.code();
        }
        IO.println(context.getCurrentDirectory());
        return ExitCode.SUCCESS.code();
    }
}
