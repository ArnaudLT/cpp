/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints;

import com.althome.lightcspsolver.solver.Sat;
import com.althome.lightcspsolver.solver.constraints.propagators.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class EqualXY implements Constraint {

    private ArrayList<Variable> variables;
    private ArrayList<Propagator> propagators;
    
    public EqualXY(Variable x, Variable y) {
        this.variables = new ArrayList<>();
        this.variables.add(x);
        this.variables.add(y);
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
        if ( this.variables.get(0).isInstantiated() && this.variables.get(1).isInstantiated() ) {
            if ( this.variables.get(0).getValue() == this.variables.get(1).getValue() ) {
                return Sat.SAT;
            } else {
                return Sat.UNSAT;
            }
        } else {
            return Sat.IDK;
        }
    }
    
}
