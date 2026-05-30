package dev.kesares.zea.cmd.executor;

/**
 * Defines a set of standard exit codes used to indicate the result of command execution.
 * These constants provide a uniform way to interpret success, errors, and other conditions
 * that arise during the execution of commands.
 * <p>
 * This is a utility class and cannot be instantiated.
 */
public enum ExitCode {

    /**
     * Exit code indicating that a command has executed successfully.
     * A value of 0 is commonly used to represent the successful completion
     * of an operation or task without any errors or interruptions.
     */
    SUCCESS(0),

    /**
     * Exit code indicating that a general error occurred during the execution of a command.
     * This code is used to represent situations where the command fails to complete successfully
     * due to errors not covered by more specific exit codes.
     */
    ERROR(1),

    /**
     * Exit code indicating that an error occurred due to improper usage of a command.
     * This code is typically used when a command is executed with invalid arguments
     * or incorrect syntax, preventing it from running correctly.
     */
    USAGE_ERROR(2),

    /**
     * Exit code indicating that the specified command exists but cannot be executed in the
     * current context or environment. This may occur due to insufficient permissions,
     * an invalid executable format, or other conditions that prevent the command from
     * being run successfully.
     */
    NOT_EXECUTABLE(126),

    /**
     * Exit code indicating that the specified command was not found.
     * This code is typically returned when an attempt is made to execute a command
     * that does not exist in the system or cannot be located in the current environment.
     * <p>
     * Common scenarios include:
     * <ul>
     *     <li>The command name is misspelled.</li>
     *     <li>The command is not installed or is unavailable in the system's {@code PATH}.</li>
     * </ul>
     */
    COMMAND_NOT_FOUND(127),

    /**
     * Exit code indicating that the execution of a command was interrupted.
     * This code is typically returned when a command is forcefully stopped
     * due to an external interruption, such as receiving a termination signal
     * (e.g., {@code SIGINT}) during its execution.
     * <p>
     * Scenarios that may result in this exit code include:
     * <ul>
     *     <li>A user interrupts the process by pressing <code>Ctrl+C</code>.</li>
     *     <li>The system sending an interruption signal to the process.</li>
     *     <li>Thread interruptions in a multithreaded environment leading to termination of the command.</li>
     * </ul>
     */
    INTERRUPTED(130);

    private final int code;
    
    ExitCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
