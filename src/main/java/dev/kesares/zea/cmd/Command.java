package dev.kesares.zea.cmd;

import dev.kesares.zea.shell.ShellContext;

/**
 * Represents a command that can be executed within a shell environment.
 * Implementations of this interface define the behavior for specific commands
 * and provide a method to execute them within a given context.
 */
public interface Command {

    /**
     * Retrieves the name of the command.
     *
     * @return the name of the command as a string
     */
    String getName();

    /**
     * Executes a command within the provided shell context. This method is invoked
     * to run implementations of specific command behavior.
     *
     * @param context the current shell context in which the command is executed,
     *                providing details such as the current directory and last exit code.
     * @param args    the arguments passed to the command, which may influence
     *                its behavior.
     */
    int execute(ShellContext context, String... args);
}
