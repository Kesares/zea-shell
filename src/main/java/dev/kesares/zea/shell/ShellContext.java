package dev.kesares.zea.shell;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {

    private boolean isRunning = true;
    private final Path currentDirectory = Paths.get("").toAbsolutePath();
    private int lastExitCode = 0;

    public boolean isRunning() {
        return isRunning;
    }

    public void stop() {
        this.isRunning = false;
    }

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