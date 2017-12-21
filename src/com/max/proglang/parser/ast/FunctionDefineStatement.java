package com.max.proglang.parser.ast;

import com.max.proglang.lib.Functions;
import com.max.proglang.lib.UserDefinedFunction;

import java.util.List;

public class FunctionDefineStatement implements Statement {

    private final String name;
    private final List<String> argNames;
    public final Statement body;

    public FunctionDefineStatement(String name, List<String> argNames, Statement body) {
        this.name = name;
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public void execute() {
        Functions.set(name, new UserDefinedFunction(argNames, body));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("def %s(%s) %s", name, argNames, body);
    }
}
