package com.max.proglang.parser.ast;

import com.max.proglang.lib.modules.Module;

public class UseStatement implements Statement {

    private final String PACKAGE = "com.max.proglang.lib.modules";

    public final Expression expression;

    public UseStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        try{
            final String moduleName = expression.eval().asString();
            final Module module = (Module) Class.forName(PACKAGE + moduleName).newInstance();
        } catch (Exception e) {
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "use" + expression;
    }
}
