package dev.kesares.zea.cmd;

import dev.kesares.zea.cmd.executor.ExitCode;
import dev.kesares.zea.shell.ShellContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PwdCommandTest {

    private final PwdCommand pwdCommand = new PwdCommand();
    private final ShellContext shellContext = new ShellContext();

    @Test
    void testReturnsSuccessWithoutArguments() {
        int exitCode = this.pwdCommand.execute(this.shellContext);

        assertEquals(exitCode, ExitCode.SUCCESS.code());
    }

    @Test
    void testReturnsUsageErrorExitCodes() {
        this.shellContext.setLastExitCode(this.pwdCommand.execute(this.shellContext, "42"));
        assertEquals(this.shellContext.getLastExitCode(), ExitCode.USAGE_ERROR.code());

        this.shellContext.setLastExitCode(this.pwdCommand.execute(this.shellContext, "hello"));
        assertEquals(this.shellContext.getLastExitCode(), ExitCode.USAGE_ERROR.code());

        this.shellContext.setLastExitCode(this.pwdCommand.execute(this.shellContext, "hello", "world"));
        assertEquals(this.shellContext.getLastExitCode(), ExitCode.USAGE_ERROR.code());
    }
}