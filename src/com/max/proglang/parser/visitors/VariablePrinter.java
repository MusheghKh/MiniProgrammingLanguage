package com.max.proglang.parser.visitors;

import com.max.proglang.parser.ast.*;

public class VariablePrinter extends AbstractVisitor {

    @Override
    public void visit(AssignmentStatement s) {
        super.visit(s);
        System.out.println(s.variable);
    }

    @Override
    public void visit(VariableExpression s) {
        System.out.println(s.name);
    }
}
