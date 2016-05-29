/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints;

import com.althome.lightcspsolver.solver.constraints.binary.EqualXY;
import com.althome.lightcspsolver.solver.constraints.binary.LessOrEqualXY;
import com.althome.lightcspsolver.solver.constraints.binary.LessXY;
import com.althome.lightcspsolver.solver.constraints.binary.NotEqualXY;
import com.althome.lightcspsolver.solver.constraints.nary.AllDifferent;
import com.althome.lightcspsolver.solver.constraints.ternary.Min;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class ConstraintFactory {
    
    public static Constraint arith(Variable x, String op, Variable y) {
        Constraint c;
        if ( op.equals("=") ) {
            c = new EqualXY(x, y);
        } else if ( op.equals(">") ) {
            c = new LessXY(y, x);
        } else if ( op.equals(">=") ) {
            c = new LessOrEqualXY(y, x);
        } else if ( op.equals("<") ) {
            c = new LessXY(x, y);
        } else if ( op.equals("<=") ) {
            c = new LessOrEqualXY(x, y);
        } else if ( op.equals("!=") ) {
            c = new NotEqualXY(x, y);
        } else {
            c = null;
        }
        return c;
    }
    
    public static Constraint allDifferent(ArrayList<Variable> variables) {
        return new AllDifferent(variables);
    }
    
    public static Constraint min(Variable min, Variable x, Variable y) {
        return new Min(min, x, y);
    }
    
    public static Constraint max(Variable max, Variable x, Variable y) {
        return new Min(max, x, y);
    }
    
}
