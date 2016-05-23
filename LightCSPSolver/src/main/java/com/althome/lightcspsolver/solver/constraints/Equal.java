/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints;

import com.althome.lightcspsolver.solver.constraints.propagators.PropEqualBC;
import com.althome.lightcspsolver.solver.constraints.propagators.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class Equal implements Constraint {

    private ArrayList<Variable> variables;
    private ArrayList<Propagator> propagators;
    
    public Equal(ArrayList<Variable> variables) {
        this.variables = variables;
        this.propagators = new ArrayList<>();
        this.propagators.add(new PropEqualBC(variables));
        for ( Variable v : variables ) {
            v.addConstraint(this);
        }
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
    public boolean isSatisfied() {
        Variable vprec = null;
        for ( Variable v : this.variables ) {
            if ( !v.isInstantiated() || 
                    ( vprec != null && v.getLowerBound() != vprec.getLowerBound()) )
                return false;
        }
        return true;
    }
    
}
