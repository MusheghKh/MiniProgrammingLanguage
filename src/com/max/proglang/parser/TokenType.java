package com.max.proglang.parser;

/**
 *
 * @author aNNiMON
 */
public enum TokenType {

    NUMBER,
    HEX_NUMBER,
    WORD,
    TEXT,

    /**
     * KEY WORDS
     * */
    PRINT,
    IF,
    ELSE,

    /**
     * OPERATORS
     * */
    PLUS,   // +
    MINUS,  // -
    STAR,   // *
    SLASH,  // /
    EQ,     // =
    LT,     // <
    GT,     // >

    LPAREN, // (
    RPAREN, // )

    EOF
}
