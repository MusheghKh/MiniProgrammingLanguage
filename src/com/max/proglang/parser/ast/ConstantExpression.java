package com.max.proglang.parser.ast;

import com.max.proglang.lib.Constants;

public class ConstantExpression implements Expression{

    private String name;

    public ConstantExpression(String name) {
        this.name = name;
    }

    @Override
    public double eval() {
        if (!Constants.isExist(name)){
            throw new RuntimeException("Constant does not exist");
        }
        return Constants.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
