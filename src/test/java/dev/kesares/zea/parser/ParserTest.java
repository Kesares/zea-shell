package dev.kesares.zea.parser;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void parseReturnsEmptyForNullInput() {
        Optional<ParsedCommand> result = this.parser.parse(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void parseReturnsEmptyForBlankInput() {
        Optional<ParsedCommand> result = this.parser.parse("   ");

        assertTrue(result.isEmpty());
    }

    @Test
    void parseParsesCommandWithoutArguments() {
        Optional<ParsedCommand> result = this.parser.parse("pwd");

        assertTrue(result.isPresent());
        assertEquals("pwd", result.get().command());
        assertArrayEquals(new String[0], result.get().args());
    }

    @Test
    void parseParsesCommandWithOneArgument() {
        Optional<ParsedCommand> result = this.parser.parse("echo hello");

        assertTrue(result.isPresent());
        assertEquals("echo", result.get().command());
        assertArrayEquals(new String[]{"hello"}, result.get().args());
    }

    @Test
    void parseParsesCommandWithMultipleArguments() {
        Optional<ParsedCommand> result = this.parser.parse("echo hello world");

        assertTrue(result.isPresent());
        assertEquals("echo", result.get().command());
        assertArrayEquals(new String[]{"hello", "world"}, result.get().args());
    }

    @Test
    void parseIgnoresLeadingAndTrailingWhitespace() {
        Optional<ParsedCommand> result = this.parser.parse("   echo hello   ");

        assertTrue(result.isPresent());
        assertEquals("echo", result.get().command());
        assertArrayEquals(new String[]{"hello"}, result.get().args());
    }

    @Test
    void parseCollapsesMultipleWhitespaceBetweenArguments() {
        Optional<ParsedCommand> result = this.parser.parse("echo     hello      world");

        assertTrue(result.isPresent());
        assertEquals("echo", result.get().command());
        assertArrayEquals(new String[]{"hello", "world"}, result.get().args());
    }

    @Test
    void parseHandlesTabsAsWhitespace() {
        Optional<ParsedCommand> result = this.parser.parse("echo\thello\tworld");

        assertTrue(result.isPresent());
        assertEquals("echo", result.get().command());
        assertArrayEquals(new String[]{"hello", "world"}, result.get().args());
    }
}