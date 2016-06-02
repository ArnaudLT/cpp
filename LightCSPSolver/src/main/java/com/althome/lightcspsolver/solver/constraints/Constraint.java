/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints;

import com.althome.lightcspsolver.solver.Sat;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public abstract class Constraint {
    
    protected ArrayList<Variable> variables;
    protected ArrayList<Propagator> propagators;
    protected boolean inPropagationQueue;
    
    public Constraint() {
        
    }
    
    public ArrayList<Variable> getVariables() {
        return this.variables;
    }
    
    public ArrayList<Propagator> getPropagators() {
        return this.propagators;
    }
    
    public void setInPropagationQueue(boolean isInPQ) {
        this.inPropagationQueue = isInPQ;
    }
    
    public boolean isInPropagationQueue() {
        return this.inPropagationQueue;
    }
    
    public abstract boolean filter();
    
    public abstract Sat isSatisfied();
    
}
