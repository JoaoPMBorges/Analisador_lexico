package com.analizador.lexisco;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private static final String[] KEYWORDS = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "inline", "int", "long", "register", "restrict", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
    private static final String OPERATORS = "+-*/=<>!&|%^~?.";
    private static final String SEPARATORS = "(),;{}[]";

    private String input;
    private int pos = 0;
    private char currentChar;
    private List<String> tokens;
    private SymbolTable symbolTable;

    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.symbolTable = new SymbolTable();
        currentChar = input.charAt(pos);
    }

    private void advance() {
        pos++;
        if (pos >= input.length()) {
            currentChar = '\0'; 
        } else {
            currentChar = input.charAt(pos);
        }
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private void addToken(TokenType type, String value) {
        tokens.add(type + ", " + value);
    }

    private void number() {
        StringBuilder num = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            num.append(currentChar);
            advance();
        }
        if (currentChar == '.') {
            num.append(currentChar);
            advance();
            while (Character.isDigit(currentChar)) {
                num.append(currentChar);
                advance();
            }
        }
        addToken(TokenType.NUMERO, num.toString());
    }

    private void stringLiteral() {
        StringBuilder str = new StringBuilder();
        advance();
        while (currentChar != '"' && currentChar != '\0') {
            str.append(currentChar);
            advance();
        }
        advance();
        addToken(TokenType.STRING, str.toString());
    }

    private void identifierOrKeyword() {
        StringBuilder id = new StringBuilder();
        if (currentChar == '#') {
            id.append(currentChar);
            advance();
        }
        while (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
            id.append(currentChar);
            advance();
        }
        String result = id.toString();
        if (result.equals("#include")) {
            addToken(TokenType.PALAVRA_RESERVADA, result);
            return;
        }
        for (String keyword : KEYWORDS) {
            if (result.equals(keyword)) {
                addToken(TokenType.PALAVRA_RESERVADA, result);
                return;
            }
        }
        int idNum = symbolTable.addSymbol(result);
        addToken(TokenType.IDENTIFICADOR, String.valueOf(idNum));
    }

    private void operator() {
        StringBuilder op = new StringBuilder();
        while (OPERATORS.indexOf(currentChar) != -1) {
            if (currentChar == '.') {
                op.append(currentChar);
                advance();
                if (Character.isLetter(currentChar)) {
                    addToken(TokenType.OPERADOR, op.toString());
                } else {
                    advance();
                }
                return;
            } else {
                op.append(currentChar);
                advance();
            }
        }
        addToken(TokenType.OPERADOR, op.toString());
    }

    private void separator() {
        char sep = currentChar;
        advance();
        addToken(TokenType.SEPARADOR, Character.toString(sep));
    }

    public List<String> tokenize() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
            } else if (Character.isDigit(currentChar)) {
                number();
            } else if (currentChar == '"') {
                stringLiteral();
            } else if (currentChar == '#') {
                identifierOrKeyword();
            } else if (Character.isLetter(currentChar) || currentChar == '_') {
                identifierOrKeyword();
            } else if (OPERATORS.indexOf(currentChar) != -1) {
                operator();
            } else if (SEPARATORS.indexOf(currentChar) != -1) {
                separator();
            } else {
                advance();
            }
        }
        return tokens;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
