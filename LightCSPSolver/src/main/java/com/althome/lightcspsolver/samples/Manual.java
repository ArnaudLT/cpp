/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.samples;

import com.althome.lightcspsolver.solver.Solver;
import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.binary.EqualXY;
import com.althome.lightcspsolver.solver.constraints.binary.GreaterXY;
import com.althome.lightcspsolver.solver.constraints.binary.LessOrEqualXY;
import com.althome.lightcspsolver.solver.constraints.nary.AllDifferent;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.VariableFactory;
import java.util.ArrayList;


/**
 *
 * @author Arnaud
 */
public class Manual {
    
    public static void main(String[] args) {

        Solver s = new Solver();
        
        for (int i=0; i<9; i++) {
            ArrayList<Variable> l = new ArrayList<>();
            for (int j=0; j<9; j++) {
                l.add(VariableFactory.enumerated(i+"_"+j, 0, 9, s));
            }
            s.post(new AllDifferent(l));
        }
        
        

        System.out.println(s.solve());
        
    }
    
}
