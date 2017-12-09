package com.max.proglang.lib;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    private static final Map<String, Double> constants;

    static {
        constants = new HashMap<>();
        constants.put("PI", Math.PI);
        constants.put("E", Math.E);
        constants.put("GOLDEN_RATIO", 1.618);
    }

    public static boolean isExist(String key){
        return constants.containsKey(key);
    }

    public static double get(String key){
        if (!isExist(key)){
            return 0;
        }
        return constants.get(key);
    }
}
