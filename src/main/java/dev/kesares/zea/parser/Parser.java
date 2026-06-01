package dev.kesares.zea.parser;

import dev.kesares.zea.parser.lexer.Lexer;
import dev.kesares.zea.parser.lexer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parser {

    public Optional<ParsedCommand> parse(String input) {
        if (input == null || input.isBlank())
            return Optional.empty();

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.lex();
        List<String> words = new ArrayList<>();

        label:
        for (Token token : tokens) {
            switch (token.type()) {
                case EOF -> { break label; }
                case WORD -> words.add(token.value());
            }
        }

        if (words.isEmpty())
            return Optional.empty();

        String command = words.getFirst();
        String[] args = words.subList(1, words.size()).toArray(String[]::new);
        return Optional.of(new ParsedCommand(command, args));
    }
}
