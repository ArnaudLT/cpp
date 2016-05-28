/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints.binary;

import com.althome.lightcspsolver.solver.Sat;
import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 * X <= Y
 * @author Arnaud
 */
public class LessXY implements Constraint {

    private ArrayList<Variable> variables;
    private ArrayList<Propagator> propagators;
    
    public LessXY(Variable x, Variable y) {
        this.variables = new ArrayList<>();
        this.variables.add(x);
        this.variables.add(y);
        this.propagators = new ArrayList<>();
        this.propagators.add(new PropLessXY(variables));
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
    public boolean filter() {
        boolean impact = false;
        for ( Propagator p : this.propagators ) {
            impact |= p.propagate();
        }
        return impact;
    }

    @Override
    public Sat isSatisfied() {
        if ( this.variables.get(0).isEmpty() || this.variables.get(1).isEmpty() ) return Sat.UNSAT;
        if ( this.variables.get(0).isInstantiated() && this.variables.get(1).isInstantiated() ) {
            if ( this.variables.get(0).getValue() < this.variables.get(1).getValue() ) {
                return Sat.SAT;
            } else {
                return Sat.UNSAT;
            }
        } else {
            return Sat.IDK;
        }
    }
    
}
