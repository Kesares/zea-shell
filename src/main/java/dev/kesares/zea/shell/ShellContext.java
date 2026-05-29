package dev.kesares.zea.shell;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {

    private final Path currentDirectory = Paths.get("").toAbsolutePath();
    private int lastExitCode = 0;

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    @SuppressWarnings("unused")
    public int getLastExitCode() {
        return lastExitCode;
    }

    public void setLastExitCode(int lastExitCode) {
        this.lastExitCode = lastExitCode;
    }
}