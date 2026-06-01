package dev.kesares.zea.parser.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;
    private final List<Token> tokens = new ArrayList<>();
    private int current = 0;

    public Lexer(String input) {
        this.input = input == null ? "" : input;
    }

    public List<Token> lex() {
        while (this.isNotAtEnd()) {
            char c = this.peek();

            if (Character.isWhitespace(c)) {
                this.advance();
                continue;
            }
            this.readWord();
        }
        this.tokens.add(new Token("", TokenType.EOF));
        return List.copyOf(this.tokens);
    }

    private void readWord() {
        StringBuilder builder = new StringBuilder();
        while (this.isNotAtEnd() && !Character.isWhitespace(this.peek())) {
            builder.append(this.advance());
        }
        this.tokens.add(new Token(builder.toString(), TokenType.WORD));
    }

    private char advance() {
        return this.input.charAt(this.current++);
    }

    private char peek() {
        return this.input.charAt(this.current);
    }

    private boolean isNotAtEnd() {
        return this.current < this.input.length();
    }
}
