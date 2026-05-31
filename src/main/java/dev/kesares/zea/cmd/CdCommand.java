package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.io.ShellIO;
import dev.kesares.zea.shell.ShellContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements Command {

    @Override
    public String getName() {
        return "cd";
    }

    @Override
    public int execute(ShellContext context, String... args) {
        Path target = args.length == 0
                ? Paths.get(System.getProperty("user.home"))
                : context.getCurrentDirectory().resolve(args[0]);

        target = target.normalize().toAbsolutePath();

        if (!Files.exists(target)) {
            ShellIO.errln(this.getName() + ": no such file or directory: " + args[0]);
            return ExitCode.ERROR.code();
        }

        if (!Files.isDirectory(target)) {
            ShellIO.errln(this.getName() + ": not a directory: " + args[0]);
            return ExitCode.ERROR.code();
        }
        context.setCurrentDirectory(target);
        return ExitCode.SUCCESS.code();
    }
}
