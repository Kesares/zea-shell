package dev.kesares.zea.shell;

import dev.kesares.zea.cmd.CommandRegistry;
import dev.kesares.zea.cmd.executor.CommandExecutor;
import dev.kesares.zea.cmd.executor.CommandExecutorDispatcher;

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
            String input = IO.readln("$ ");

            if (input == null || input.isBlank())
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
