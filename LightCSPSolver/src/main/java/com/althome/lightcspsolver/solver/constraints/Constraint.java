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
public interface Constraint {
    
    public ArrayList<Variable> getVariables();
    public ArrayList<Propagator> getPropagators();
    public void filter();
    public Sat isSatisfied();
    
}
