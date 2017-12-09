package com.max.proglang;

import com.max.proglang.parser.Lexer;
import com.max.proglang.parser.Parser;
import com.max.proglang.parser.Token;
import com.max.proglang.parser.ast.Expression;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String input = "2 + 2";
        final String input2 = "(2 + 2) * #f";

        final List<Token> tokens = new Lexer(input2).tokenize();
        for (Token token : tokens){
            System.out.println(token.toString());
        }

        System.out.println();

        final List<Expression> expressions = new Parser(tokens).parse();
        for (Expression expr : expressions){
            System.out.println(expr + " = " + expr.eval());
        }
    }
}
