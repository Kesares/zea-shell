package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;

public class EchoCommand implements Command {

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public int execute(ShellContext context, String... args) {
        for (String arg : args)
            IO.print(arg);
        IO.println();
        return ExitCode.SUCCESS.code();
    }
}
