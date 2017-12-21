package com.max.proglang;

import com.max.proglang.parser.Lexer;
import com.max.proglang.parser.Parser;
import com.max.proglang.parser.Token;
import com.max.proglang.parser.ast.Statement;
import com.max.proglang.parser.visitors.AssignValidator;
import com.max.proglang.parser.visitors.FunctionAdder;
import com.max.proglang.parser.visitors.VariablePrinter;

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

        final Statement program = new Parser(tokens).parse();
        System.out.println(program.toString());
        program.accept(new FunctionAdder());
        program.accept(new VariablePrinter());
        program.accept(new AssignValidator());
        program.execute();
    }
}
