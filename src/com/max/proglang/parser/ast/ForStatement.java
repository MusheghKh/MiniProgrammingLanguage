package com.max.proglang.parser.ast;

public class ForStatement implements Statement{

    private final Statement initialization;
    private final Expression termination;
    private final Statement increment;
    private final Statement statement;

    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement statement) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.statement = statement;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().asDouble() != 0; increment.execute()){
            statement.execute();
        }
    }

    @Override
    public String toString() {
        return "for " + initialization + ", " + termination + ", " + increment + " " + statement;
    }
}
