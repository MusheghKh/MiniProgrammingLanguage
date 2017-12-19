package com.max.proglang.lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private static final NumberValue ZERO = new NumberValue(0);

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
            return ZERO;
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
