package com.max.proglang.parser.ast;

import com.max.proglang.lib.ArrayValue;
import com.max.proglang.lib.Value;
import com.max.proglang.lib.Variables;

public class ArrayAssignmentStatement implements Statement {

    private final String variable;
    private final Expression index;
    private final Expression expression;

    public ArrayAssignmentStatement(String variable, Expression index, Expression expression) {
        this.variable = variable;
        this.index = index;
        this.expression = expression;
    }

    @Override
    public void execute() {
        final Value var = Variables.get(variable);
        if (var instanceof ArrayValue){
            final ArrayValue array = (ArrayValue) var;
            array.set((int) index.eval().asDouble(), expression.eval());
        } else {
            throw new RuntimeException("Array excepted");
        }
    }

    @Override
    public String toString() {
        return String.format("%s[%s] = %s", variable, index, expression);
    }
}
