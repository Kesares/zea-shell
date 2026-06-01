package dev.kesares.zea.parser.lexer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void testLexReturnsOnlyEofForEmptyInput() {
        Lexer lexer = new Lexer("");
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(new Token("", TokenType.EOF)), tokens);
    }

    @Test
    void testLexReturnsOnlyEofForBlankInput() {
        Lexer lexer = new Lexer("   \t   ");
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(new Token("", TokenType.EOF)), tokens);
    }

    @Test
    void textLexesSingleWord() {
        Lexer lexer = new Lexer("pwd");
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(
                new Token("pwd", TokenType.WORD),
                new Token("", TokenType.EOF)
        ), tokens);
    }

    @Test
    void textLexesMultipleWords() {
        Lexer lexer = new Lexer("echo hello world");
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(
            new Token("echo", TokenType.WORD),
            new Token("hello", TokenType.WORD),
            new Token("world", TokenType.WORD),
            new Token("", TokenType.EOF)
        ), tokens);
    }

    @Test
    void textLexesMultipleWordsSingleWord() {
        Lexer lexer = new Lexer("echo    hello\t\tworld");
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(
                new Token("echo", TokenType.WORD),
                new Token("hello", TokenType.WORD),
                new Token("world", TokenType.WORD),
                new Token("", TokenType.EOF)
        ), tokens);
    }

    @Test
    void testTreatsNullInputAsEmptyInput() {
        Lexer lexer = new Lexer(null);
        List<Token> tokens = lexer.lex();

        assertEquals(List.of(new Token("", TokenType.EOF)), tokens);
    }
}