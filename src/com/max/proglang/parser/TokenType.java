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
     * KEYWORDS
     * */
    PRINT,
    IF,
    ELSE,
    WHILE,
    FOR,
    DO,
    BREAK,
    CONTINUE,
    DEF,
    RETURN,

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
    LBRACKET,   // [
    RBRACKET,   // ]
    LBRACE,     // {
    RBRACE,     // }
    COMMA,      // ,

    EOF
}
