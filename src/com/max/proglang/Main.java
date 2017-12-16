package com.max.proglang;

import com.max.proglang.parser.Lexer;
import com.max.proglang.parser.Parser;
import com.max.proglang.parser.Token;
import com.max.proglang.parser.ast.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String input = new String(Files.readAllBytes(Paths.get("program.txt")), "UTF-8");

        final List<Token> tokens = new Lexer(input).tokenize();
        for (Token token : tokens){
            System.out.println(token.toString());
        }

        System.out.println();

        final List<Statement> statements = new Parser(tokens).parse();
        for (Statement statement : statements){
            System.out.println(statement);
        }

        System.out.println();

        for (Statement statement : statements){
            statement.execute();
        }

    }
}
