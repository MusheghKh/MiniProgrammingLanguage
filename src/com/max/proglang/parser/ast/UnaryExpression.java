package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.Value;

public final class UnaryExpression implements Expression {

    public enum Operator {
        NEGATE("-"),

        // Boolean
        NOT("!"),

        // Bitwise
        COMPLEMENT("~");

        private final String name;

        private Operator(String name){
            this.name = name;
        }


        @Override
        public String toString() {
            return name;
        }
    }

    public final Expression expr1;
        private final Operator operation;

    public UnaryExpression(Operator operation, Expression expr1) {
        this.operation = operation;
        this.expr1 = expr1;
    }

    @Override
    public Value eval() {
        final Value value = expr1.eval();
        switch (operation) {
            case NEGATE: return new NumberValue(-value.asDouble());
            case COMPLEMENT: return new NumberValue(~(int)value.asDouble());
            case NOT: return new NumberValue(value.asDouble() != 0 ? 0 : 1);
            default:
                throw new RuntimeException("Operation " + operation + " is not supported");
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", operation, expr1);
    }
}
