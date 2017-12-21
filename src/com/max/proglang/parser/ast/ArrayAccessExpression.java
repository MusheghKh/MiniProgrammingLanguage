package com.max.proglang.parser.ast;

import com.max.proglang.lib.ArrayValue;
import com.max.proglang.lib.Value;
import com.max.proglang.lib.Variables;

public class ArrayAccessExpression implements Expression {

    private final String variable;
    private final Expression index;

    public ArrayAccessExpression(String variable, Expression index) {
        this.variable = variable;
        this.index = index;
    }

    @Override
    public Value eval() {
        final Value var = Variables.get(variable);
        if (var instanceof ArrayValue){
            final ArrayValue array = (ArrayValue) var;
            return array.get((int) index.eval().asDouble());
        }else {
            throw new RuntimeException("Array excepted");
        }
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", variable, index);
    }
}
