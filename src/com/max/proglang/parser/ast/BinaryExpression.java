package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.StringValue;
import com.max.proglang.lib.Value;

/**
 *
 * @author aNNiMON
 */
public final class BinaryExpression implements Expression {
    
    private final Expression expr1, expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        final Value v1 = expr1.eval();
        final Value v2 = expr2.eval();
        if (v1 instanceof StringValue) {
            final String s1 = v1.asString();

            switch (operation) {
                case '*':
                    final int iterations = (int) v2.asDouble();
                    final StringBuilder buffer = new StringBuilder();
                    for (int i = 0; i < iterations; i++) {
                        buffer.append(s1);
                    }
                    return new StringValue(buffer.toString());
                case '+':
                default:
                    return new StringValue(s1 + v2.asString());
            }
        }

        final double n1 = expr1.eval().asDouble();
        final double n2 = expr2.eval().asDouble();
        switch (operation) {
            case '-': return new NumberValue(n1 - n2);
            case '*': return new NumberValue(n1 * n2);
            case '/': return new NumberValue(n1 / n2);
            case '+':
            default:
                return new NumberValue(n1 + n2);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}
