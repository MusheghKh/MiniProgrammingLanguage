package com.max.proglang.parser;

public class LexerException extends RuntimeException {

    public LexerException() {
        super();
    }

    public LexerException(int row, int col, String message) {
        super("[" + row + ":" + col + "] " + message);
    }
}
