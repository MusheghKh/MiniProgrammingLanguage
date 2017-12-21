package com.max.proglang.lib;

public class NumberValue implements Value {

    public static final NumberValue ZERO = new NumberValue(0);
    public static final NumberValue ONE = new NumberValue(1);

    private final double value;

    public NumberValue(double value) {
        this.value = value;
    }

    public static NumberValue fromBoolean(boolean b){
        return b ? ONE : ZERO;
    }

    @Override
    public double asDouble() {
        return value;
    }

    @Override
    public String asString() {
        return Double.toString(value);
    }

    @Override
    public String toString() {
        return asString();
    }
}
