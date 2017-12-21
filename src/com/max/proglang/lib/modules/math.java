package com.max.proglang.lib.modules;

import com.max.proglang.lib.Function;
import com.max.proglang.lib.Functions;
import com.max.proglang.lib.NumberValue;
import com.max.proglang.lib.Variables;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class math implements Module {

    private static final DoubleFunction<NumberValue> doubleToNumber = NumberValue::new;

    @Override
    public void init() {
        Functions.set("abs", functionConvert(Math::abs));
        Functions.set("cos", functionConvert(Math::cos));
        Functions.set("sin", functionConvert(Math::sin));
        Functions.set("sqrt", functionConvert(Math::sqrt));
        Functions.set("toDegrees", functionConvert(Math::toDegrees));
        Functions.set("toRadians", functionConvert(Math::toRadians));

        Functions.set("pow", biFunctionConvert(Math::pow));
        Functions.set("atan2", biFunctionConvert(Math::atan2));

        Variables.set("PI", new NumberValue(Math.PI));
        Variables.set("E", new NumberValue(Math.E));
    }

    private static Function functionConvert(DoubleUnaryOperator op) {
        return args -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return doubleToNumber.apply(op.applyAsDouble(args[0].asDouble()));
        };
    }

    private static Function biFunctionConvert(DoubleBinaryOperator op) {
        return args -> {
            if (args.length != 2) throw new RuntimeException("Two args expected");
            return doubleToNumber.apply(op.applyAsDouble(args[0].asDouble(), args[1].asDouble()));
        };
    }
}