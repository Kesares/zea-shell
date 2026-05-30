package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalCommandExecutor implements CommandExecutor {

    @Override
    public void execute(ShellContext context, String command, String... args) {
        List<String> commandParts = new ArrayList<>();
        commandParts.add(command);
        commandParts.addAll(List.of(args));

        ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
        processBuilder.directory(context.getCurrentDirectory().toFile());
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            IO.println("Command not found: " + command);
            context.setLastExitCode(127);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            context.setLastExitCode(130);
        }
    }
}
