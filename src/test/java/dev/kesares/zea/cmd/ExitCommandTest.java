package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    private final ExitCommand exitCommand = new ExitCommand();
    private final ShellContext shellContext = new ShellContext();

    @Test
    void testExitWithoutArgumentsStopsContext() {
        this.shellContext.setLastExitCode(this.exitCommand.execute(this.shellContext));
        assertEquals(ExitCode.SUCCESS.code(), this.shellContext.getLastExitCode());
    }

    @Test
    void testExitWithArgumentsStopsContext() {
        this.shellContext.setLastExitCode(this.exitCommand.execute(this.shellContext, "42"));
        assertEquals(42, this.shellContext.getLastExitCode());

        this.shellContext.setLastExitCode(this.exitCommand.execute(this.shellContext, ""));
        assertEquals(ExitCode.USAGE_ERROR.code(), this.shellContext.getLastExitCode());
    }

    @Test
    void testExitWithMoreThanOneArgumentsReturnsUsageError() {
        this.shellContext.setLastExitCode(this.exitCommand.execute(this.shellContext, "42", "0"));
        assertEquals(ExitCode.USAGE_ERROR.code(), this.shellContext.getLastExitCode());

        this.shellContext.setLastExitCode(this.exitCommand.execute(this.shellContext, "", "0"));
        assertEquals(ExitCode.USAGE_ERROR.code(), this.shellContext.getLastExitCode());
    }
}