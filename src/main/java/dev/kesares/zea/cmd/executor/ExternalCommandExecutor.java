package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

import java.io.IOException;

public class ExternalCommandExecutor implements CommandExecutor {

    @Override
    public void execute(ShellContext context, String... args) {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        processBuilder.directory(context.getCurrentDirectory().toFile());
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            IO.println("Command not found: " + args[0]);
            context.setLastExitCode(127);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            context.setLastExitCode(130);
        }
    }
}
