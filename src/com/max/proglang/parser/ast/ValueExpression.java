package com.max.proglang.parser.ast;

import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.StringValue;
import com.max.proglang.lib.Value;

import java.util.Arrays;

/**
 *
 * @author aNNiMON
 */
public final class ValueExpression implements Expression {
    
    private final Value value;
    
    public ValueExpression(double value) {
        this.value = new NumberValue(value);
    }

    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }

    @Override
    public Value eval() {
        return value;
    }

    @Override
    public String toString() {
        return value.asString();
    }
}
