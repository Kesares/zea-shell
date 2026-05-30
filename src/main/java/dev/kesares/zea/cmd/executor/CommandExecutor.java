package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

/**
 * Represents a contract for classes that can execute commands within a specific shell context.
 */
public interface CommandExecutor {

    /**
     * Executes a specified command within the given shell context, optionally with arguments.
     *
     * @param context the shell context in which the command is executed
     * @param command the name or path of the command to execute
     * @param args    optional arguments to pass to the command
     */
    void execute(ShellContext context, String command, String... args);
}
