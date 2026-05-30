package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalCommandExecutor implements CommandExecutor {

    @Override
    public int execute(ShellContext context, String command, String... args) {
        List<String> commandParts = new ArrayList<>();
        commandParts.add(command);
        commandParts.addAll(List.of(args));

        ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
        processBuilder.directory(context.getCurrentDirectory().toFile());
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            return process.waitFor();
        } catch (IOException e) {
            IO.println(command + ": command not found");
            return ExitCode.COMMAND_NOT_FOUND.code();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ExitCode.INTERRUPTED.code();
        }
    }
}
