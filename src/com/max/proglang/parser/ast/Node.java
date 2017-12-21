package com.max.proglang.parser.ast;

public interface Node {

    void accept(Visitor visitor);
}
