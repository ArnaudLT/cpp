/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.samples;

import com.althome.lightcspsolver.solver.Solver;
import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.EqualXY;
import com.althome.lightcspsolver.solver.constraints.GreaterXY;
import com.althome.lightcspsolver.solver.constraints.LessOrEqualXY;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.VariableFactory;


/**
 *
 * @author Arnaud
 */
public class Manual {
    
    public static void main(String[] args) {

        Solver s = new Solver();
        
        Variable v1 = VariableFactory.enumerated("v1", 1, 3, s);
        Variable v2 = VariableFactory.enumerated("v2", 1, 3, s);
        Variable v3 = VariableFactory.enumerated("v3", 0, 3, s);
        
        // v1 > v2 and v2 < v3
        Constraint c1 = new GreaterXY(v1, v2);
        s.post(c1);
        
        Constraint c2 = new GreaterXY(v3, v2);
        s.post(c2);
        
        s.solve();
        
        System.out.println(s);
        
    }
    
}
