package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.StringValue;
import com.max.proglang.lib.Value;

public class ConditionalExpression implements Expression{

    private Expression expr1, expr2;
    private char operation;

    public ConditionalExpression(char operation, Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operation = operation;
    }

    @Override
    public Value eval() {
        final Value v1 = expr1.eval();
        final Value v2 = expr2.eval();
        if (v1 instanceof StringValue) {
            final String s1 = v1.asString();
            final String s2 = v2.asString();

            switch (operation) {
                case '<': return new NumberValue(s1.compareTo(s2) < 0);
                case '>': return new NumberValue(s1.compareTo(s2) > 0);
                case '=':
                default:
                    return new NumberValue(s1.equals(s2));
            }
        }

        final double n1 = expr1.eval().asDouble();
        final double n2 = expr2.eval().asDouble();
        switch (operation) {
            case '<': return new NumberValue(n1 < n2);
            case '>': return new NumberValue(n1 > n2);
            case '=':
            default:
                return new NumberValue(n1 == n2);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}
