package com.max.proglang.parser.ast;

import com.max.proglang.lib.Value;
import com.max.proglang.lib.Variables;

public class VariableExpression implements Expression{

    public String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!Variables.isExist(name)){
            throw new RuntimeException("Constant does not exist: " + name);
        }
        return Variables.get(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
