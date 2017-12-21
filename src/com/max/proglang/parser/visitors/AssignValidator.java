package com.max.proglang.parser.visitors;

import com.max.proglang.lib.Variables;
import com.max.proglang.parser.ast.*;

public class AssignValidator extends AbstractVisitor {

    @Override
    public void visit(AssignmentStatement s) {
        super.visit(s);
        if (Variables.isExist(s.variable)){
            throw new RuntimeException("Can not assign value to constant");
        }
    }
}
