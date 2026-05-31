package dev.kesares.zea.shell;

import dev.kesares.zea.cmd.CommandRegistry;
import dev.kesares.zea.cmd.executor.CommandExecutor;
import dev.kesares.zea.cmd.executor.CommandExecutorDispatcher;
import dev.kesares.zea.io.ShellIO;
import dev.kesares.zea.parser.CommandLineParser;
import dev.kesares.zea.parser.ParsedCommand;

import java.util.Optional;

public enum Shell {

    INSTANCE;

    private final ShellContext shellContext = new ShellContext();
    private final CommandLineParser commandLineParser = new CommandLineParser();
    private final CommandRegistry commandRegistry = new CommandRegistry();
    private final CommandExecutorDispatcher commandExecutorDispatcher = new CommandExecutorDispatcher(this.commandRegistry);

    public void run() {
        while (this.shellContext.isRunning()) {
            String input = ShellIO.readln("$ ");

            if (input == null)
                break;

            Optional<ParsedCommand> optParsed = this.commandLineParser.parse(input);

            if (optParsed.isEmpty())
                continue;

            ParsedCommand parsed = optParsed.get();
            CommandExecutor executor = this.commandExecutorDispatcher.resolveCommandExecutor(parsed.command());
            int exitCode = executor.execute(this.shellContext, parsed.command(), parsed.args());
            this.shellContext.setLastExitCode(exitCode);
        }
    }
}
