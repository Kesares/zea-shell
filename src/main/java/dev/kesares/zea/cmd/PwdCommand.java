package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.io.ShellIO;
import dev.kesares.zea.shell.ShellContext;

public class PwdCommand implements Command {

    @Override
    public String getName() {
        return "pwd";
    }

    @Override
    public int execute(ShellContext context, String... args) {
        if (args.length > 0) {
            ShellIO.errln(this.getName() + ": too many arguments");
            return ExitCode.USAGE_ERROR.code();
        }
        ShellIO.println(context.getCurrentDirectory());
        return ExitCode.SUCCESS.code();
    }
}
