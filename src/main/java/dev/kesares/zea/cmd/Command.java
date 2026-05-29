package dev.kesares.zea.cmd;

import dev.kesares.zea.shell.ShellContext;

public interface Command {

    String getId();

    void execute(ShellContext context, String... args);
}
