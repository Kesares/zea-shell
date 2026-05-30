package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;

public class ExitCommand implements Command {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public int execute(ShellContext context, String... args) {
        if (args.length > 1) {
            IO.println(this.getName() +  ": too many arguments");
            return ExitCode.USAGE_ERROR.code();
        }
        context.stop();
        return ExitCode.SUCCESS.code();
    }
}
