package com.max.proglang.parser.ast;

import com.max.proglang.lib.Value;

/**
 *
 * @author aNNiMON
 */
public interface Expression extends Node {
    
    Value eval();
}
