/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver;

import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class Solver {
    
    private ArrayList<Variable> variables;
    private ArrayList<Constraint> constraints;
    
    
    public Solver() {
        
    }
    
    public void post(Constraint constraint ) {
        this.variables.addAll(constraint.getVariables());
        this.constraints.add(constraint);
    }
    
    public boolean isASolution() {
        for ( Constraint c : this.constraints ) {
            if ( !c.isSatisfied() ) return false;
        }
        return true;
    }
    
    
    
    
    
}
