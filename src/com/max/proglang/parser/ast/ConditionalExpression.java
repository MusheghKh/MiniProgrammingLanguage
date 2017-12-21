package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.StringValue;
import com.max.proglang.lib.Value;

public class ConditionalExpression implements Expression {

    public enum Operator {
        EQUALS("=="),
        NOT_EQUALS("!="),

        LT("<"),
        LTEQ("<="),
        GT(">"),
        GTEQ(">="),

        AND("&&"),
        OR("||");

        private final String name;

        public String getName() {
            return name;
        }

        Operator(String name) {
            this.name = name;
        }
    }

    public Expression expr1, expr2;
    public Operator operator;

    public ConditionalExpression(Operator operator, Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public Value eval() {
        final Value v1 = expr1.eval();
        final Value v2 = expr2.eval();

        switch (operator) {
            case AND:
                return NumberValue.fromBoolean(
                        (v1.asDouble() != 0) && v2.asDouble() != 0
                );
            case OR:
                return NumberValue.fromBoolean(
                        (v1.asDouble() != 0) && (v2.asDouble() != 0)
                );
        }

        double n1, n2;
        if (v1 instanceof StringValue) {
            n1 = v1.asString().compareTo(v2.asString());
            n2 = 0;
        } else {
            n1 = v1.asDouble();
            n2 = v2.asDouble();
        }

        boolean result;
        switch (operator) {
            case EQUALS:
                result = n1 == n2;
                break;
            case NOT_EQUALS:
                result = n1 != n2;
                break;
            case LT:
                result = n1 < n2;
                break;
            case LTEQ:
                result = n1 <= n2;
                break;
            case GT:
                result = n1 > n2;
                break;
            case GTEQ:
                result = n1 >= n2;
                break;
            default:
                throw new RuntimeException("Operation " + operator + " is not supported");
        }
        return NumberValue.fromBoolean(result);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", expr1, operator.getName(), expr2);
    }
}
