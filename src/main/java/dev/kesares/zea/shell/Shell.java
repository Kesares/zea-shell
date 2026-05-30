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
        while (true) {
            String prompt = IO.readln("$ ");

            if (prompt == null || prompt.equals("exit"))
                break;

            if (prompt.isBlank())
                continue;

            String[] parts = prompt.split(" ");
            String command = parts[0];
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);

            CommandExecutor executor = this.commandExecutorDispatcher.resolveCommandExecutor(parts[0]);
            executor.execute(this.shellContext, command, args);
        }
    }
}
