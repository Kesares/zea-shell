package dev.kesares.zea.cmd.executor;

import dev.kesares.zea.shell.ShellContext;

public interface CommandExecutor {

    void execute(ShellContext context, String... args);
}
