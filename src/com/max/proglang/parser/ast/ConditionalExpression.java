package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.StringValue;
import com.max.proglang.lib.Value;

public class ConditionalExpression implements Expression{

    public static enum OPERATOR {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),

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

        OPERATOR(String name) {
            this.name = name;
        }
    }

    public Expression expr1, expr2;
    public OPERATOR operator;

    public ConditionalExpression(OPERATOR operator, Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public Value eval() {
        final Value v1 = expr1.eval();
        final Value v2 = expr2.eval();

        double n1, n2;
        if (v1 instanceof StringValue) {
            n1 = v1.asString().compareTo(v2.asString());
            n2 = 0;
        }else {
            n1 = v1.asDouble();
            n2 = v2.asDouble();
        }

        boolean result;
        switch (operator) {
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
            case NOT_EQUALS:
                result = n1 != n2;
                break;
            case AND:
                result = (n1 != 0) && (n2 != 0);
                break;
            case OR:
                result = (n1 != 0) || (n2 != 0);
                break;
            case EQUALS:
            default:
                result = n1 == n2;
                break;
        }
        return new NumberValue(result);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("[%s %s %s]", expr1, operator.getName(), expr2);
    }
}
