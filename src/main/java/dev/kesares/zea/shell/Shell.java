package dev.kesares.zea.shell;

import dev.kesares.zea.cmd.CommandRegistry;
import dev.kesares.zea.cmd.executor.CommandExecutor;
import dev.kesares.zea.cmd.executor.CommandExecutorDispatcher;
import dev.kesares.zea.io.ShellIO;

import java.util.Arrays;

public enum Shell {

    INSTANCE;

    private final CommandRegistry commandRegistry = new CommandRegistry();
    private final CommandExecutorDispatcher commandExecutorDispatcher = new CommandExecutorDispatcher(this.commandRegistry);
    private final ShellContext shellContext = new ShellContext();

    public void init() {
        this.commandRegistry.init();
    }

    public void run() {
        while (this.shellContext.isRunning()) {
            String input = ShellIO.readln("$ ");

            if (input == null)
                break;

            if (input.isBlank())
                continue;

            String[] parts = input.trim().split("\\s+");
            String command = parts[0];
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);

            CommandExecutor executor = this.commandExecutorDispatcher.resolveCommandExecutor(command);
            int exitCode = executor.execute(this.shellContext, command, args);
            this.shellContext.setLastExitCode(exitCode);
        }
    }
}
