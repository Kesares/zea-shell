package dev.kesares.zea.shell;

import dev.kesares.zea.cmd.executor.ExitCode;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {

    private boolean isRunning = true;
    private Path currentDirectory = Paths.get("").toAbsolutePath();
    private int lastExitCode = ExitCode.SUCCESS.code();

    public boolean isRunning() {
        return isRunning;
    }

    public void stop() {
        this.isRunning = false;
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Path currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    @SuppressWarnings("unused")
    public int getLastExitCode() {
        return lastExitCode;
    }

    public void setLastExitCode(int lastExitCode) {
        this.lastExitCode = lastExitCode;
    }
}