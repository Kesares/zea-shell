package dev.kesares.zea.parser;

import java.util.Arrays;
import java.util.Optional;

public class Parser {

    public Optional<ParsedCommand> parse(String input) {
        if (input == null || input.isBlank())
            return Optional.empty();

        String[] parts = input.trim().split("\\s+");
        String command = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        return Optional.of(new ParsedCommand(command, args));
    }
}
