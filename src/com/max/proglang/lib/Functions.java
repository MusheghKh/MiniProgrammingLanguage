package com.max.proglang.lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private static final Map<String, Function> functions = new HashMap<>();

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
