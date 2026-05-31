package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CdCommandTest {

    private final CdCommand cdCommand = new CdCommand();
    private final ShellContext shellContext = new ShellContext();

    @Test
    void executeWithoutArgumentsChangesToHomeDirectory() {
        int exitCode = this.cdCommand.execute(this.shellContext);

        assertEquals(ExitCode.SUCCESS.code(), exitCode);
        assertEquals(
                Paths.get(System.getProperty("user.home")).normalize().toAbsolutePath(),
                this.shellContext.getCurrentDirectory()
        );
    }

    @Test
    void executeWithExistingDirectoryChangesCurrentDirectory(@TempDir Path tempDir) {
        int exitCode = this.cdCommand.execute(this.shellContext, tempDir.toString());

        assertEquals(ExitCode.SUCCESS.code(), exitCode);
        assertEquals(tempDir.normalize().toAbsolutePath(), this.shellContext.getCurrentDirectory());
    }

    @Test
    void executeWithMissingDirectoryReturnsErrorAndKeepsCurrentDirectory(@TempDir Path tempDir) {
        Path initialDirectory = this.shellContext.getCurrentDirectory();
        Path missingDirectory = tempDir.resolve("missing");

        int exitCode = this.cdCommand.execute(this.shellContext, missingDirectory.toString());

        assertEquals(ExitCode.ERROR.code(), exitCode);
        assertEquals(initialDirectory, this.shellContext.getCurrentDirectory());
    }

    @Test
    void executeWithFileReturnsErrorAndKeepsCurrentDirectory(@TempDir Path tempDir) throws IOException {
        Path initialDirectory = this.shellContext.getCurrentDirectory();
        Path file = Files.createFile(tempDir.resolve("file.txt"));

        int exitCode = this.cdCommand.execute(this.shellContext, file.toString());

        assertEquals(ExitCode.ERROR.code(), exitCode);
        assertEquals(initialDirectory, this.shellContext.getCurrentDirectory());
    }
}