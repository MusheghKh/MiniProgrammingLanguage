package com.max.proglang.lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private static final Map<String, Function> functions;

    static {
        functions = new HashMap<>();
        functions.put("sin", args -> {
            if (args.length != 1){
                throw new RuntimeException("One args excepted");
            }
            return new NumberValue(Math.sin(args[0].asDouble()));
        });
        functions.put("cos", args -> {
            if (args.length != 1){
                throw new RuntimeException("One args excepted");
            }
            return new NumberValue(Math.cos(args[0].asDouble()));
        });
        functions.put("echo", args -> {
            for (Value arg : args){
                System.out.println(arg.asString());
            }
            return NumberValue.ZERO;
        });
        functions.put("newarray", new Function() {

            @Override
            public Value execute(Value... args) {
                return createArray(args, 0);
            }

            private ArrayValue createArray(Value[] args, int index) {
                final int size = (int) args[index].asDouble();
                final int last = args.length - 1;
                ArrayValue array = new ArrayValue(size);
                if (index == last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, NumberValue.ZERO);
                    }
                } else if (index < last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, createArray(args, index + 1));
                    }
                }
                return array;
            }
        });
    }

    public static boolean isExist(String key){
        return functions.containsKey(key);
    }

    public static Function get(String key){
        if (!isExist(key)){
            throw new RuntimeException("Unknown Exception " +  key);
        }
        return functions.get(key);
    }

    public static void set(String key, Function function){
        functions.put(key, function);
    }
}
