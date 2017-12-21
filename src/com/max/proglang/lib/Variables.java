package com.max.proglang.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class Variables {

    private static final Stack<Map<String, Value>> stack;

    private static Map<String, Value> variables;

    static {
        stack = new Stack<>();
        variables = new ConcurrentHashMap<>();
        variables.put("true", NumberValue.ONE);
        variables.put("false", NumberValue.ZERO);
    }

    public static void push(){
        stack.push(new ConcurrentHashMap<>(variables));
    }

    public static void pop(){
        variables = stack.pop();
    }

    public static boolean isExist(String key){
        return variables.containsKey(key);
    }

    public static Value get(String key){
        if (!isExist(key)){
            return NumberValue.ZERO;
        }
        return variables.get(key);
    }

    public static void set(String key, Value value){
        variables.put(key, value);
    }
}
