package com.max.proglang.parser.ast;

import com.max.proglang.lib.Value;

public class ReturnStatement extends RuntimeException implements Statement {

    private final Expression expression;
    private Value result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public Value getResult(){
        return result;
    }

    @Override
    public void execute() {
        result = expression.eval();
        throw this;
    }

    @Override
    public String toString() {
        return "return";
    }
}
