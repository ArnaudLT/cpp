/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints.ternary;

import com.althome.lightcspsolver.solver.constraints.binary.*;
import com.althome.lightcspsolver.solver.Sat;
import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 * X = MIN(Y,Z)
 * @author Arnaud
 */
public class Min implements Constraint {

    private ArrayList<Variable> variables;
    private ArrayList<Propagator> propagators;
    
    public Min(Variable x, Variable y, Variable z) {
        this.variables = new ArrayList<>();
        this.variables.add(x);
        this.variables.add(y);
        this.variables.add(z);
        this.propagators = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Variable> getVariables() {
        return this.variables; 
    }

    @Override
    public ArrayList<Propagator> getPropagators() {
        return this.propagators;
    }

    @Override
    public void filter() {
        for ( Propagator p : this.propagators ) {
            p.propagate();
        }
    }

    @Override
    public Sat isSatisfied() {
        if ( !this.variables.get(0).isInstantiated() ||
             !this.variables.get(1).isInstantiated() ||
             !this.variables.get(2).isInstantiated() ) {
            return Sat.IDK;
        } else if ( this.variables.get(0).getValue() == 
                Math.min(this.variables.get(1).getValue(), this.variables.get(2).getValue()) ) {
            return Sat.SAT;
        } else {
            return Sat.UNSAT;
        }
        
    }
    
}
