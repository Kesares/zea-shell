package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

/**
 * Represents a contract for classes that can execute commands within a specific shell context.
 */
public interface CommandExecutor {

    /**
     * Executes a command within the provided shell context.
     *
     * @param context the current shell context in which the command is executed. Must not be null.
     * @param command the name of the command to execute. Must not be null or blank.
     * @param args optional arguments passed to the command. Maybe empty but not null.
     * @return the exit code resulting from the command execution. A value of 0 typically indicates
     *         successful execution, while non-zero values represent various failure conditions.
     */
    int execute(ShellContext context, String command, String... args);
}
