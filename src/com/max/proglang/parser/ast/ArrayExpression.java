package com.max.proglang.parser.ast;

import com.max.proglang.lib.*;

import java.util.List;

public class ArrayExpression implements Expression{

    private final List<Expression> elements;

    public ArrayExpression(List<Expression> elements) {
        this.elements = elements;
    }

    @Override
    public Value eval() {
        final int size = elements.size();
        final ArrayValue array = new ArrayValue(size);
        for (int i = 0; i < size; i++){
            array.set(i, elements.get(i).eval());
        }
        return array;
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
