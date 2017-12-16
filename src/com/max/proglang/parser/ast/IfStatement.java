package com.max.proglang.parser.ast;

public class IfStatement implements Statement{

    private final Expression expression;
    private final Statement ifStatment, elseStatment;

    public IfStatement(Expression expression, Statement ifStatment, Statement elseStatment) {
        this.expression = expression;
        this.ifStatment = ifStatment;
        this.elseStatment = elseStatment;
    }

    @Override
    public void execute() {
        final double result = expression.eval().asDouble();
        if (result != 0){
            ifStatment.execute();
        }else {
            elseStatment.execute();
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("if").append(expression).append(' ').append(ifStatment);
        if (elseStatment != null){
            result.append("\nelse").append(elseStatment);
        }
        return result.toString();
    }
}
