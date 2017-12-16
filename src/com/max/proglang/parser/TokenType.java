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
    WHILE,
    FOR,

    /**
     * OPERATORS
     * */
    PLUS,       // +
    MINUS,      // -
    STAR,       // *
    SLASH,      // /
    EQ,         // =
    EQEQ,       // ==
    EXCL,       // !
    EXCLEQ,     // !=
    LT,         // <
    LTEQ,       // <=
    GT,         // >
    GTEQ,       // >=

    BAR,        // |
    BARBAR,     // ||
    AMP,        // &
    AMPAMP,     // &&

    LPAREN,     // (
    RPAREN,     // )
    LBRACE,     // {
    RBRACE,     // }
    COMMA,      // ,

    EOF
}
