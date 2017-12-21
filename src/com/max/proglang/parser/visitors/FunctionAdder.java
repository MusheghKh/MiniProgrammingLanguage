package com.max.proglang.parser.visitors;

import com.max.proglang.parser.ast.*;

public class FunctionAdder extends AbstractVisitor {

    @Override
    public void visit(FunctionDefineStatement s) {
        super.visit(s);
        s.execute();
    }
}
