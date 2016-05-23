/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints.propagators;

import com.althome.lightcspsolver.solver.variables.IntVariable;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class PropEqualBC implements Propagator {

    private ArrayList<Variable> variables;
    
    public PropEqualBC(ArrayList<Variable> variables) {
        this.variables = variables;
    }
  
    @Override
    public void propagate() {
        int maxLB = Integer.MIN_VALUE;
        int minUB = Integer.MAX_VALUE;
        for ( Variable v : this.variables ) {
            if ( v.getLowerBound() > maxLB ) {
                maxLB = v.getLowerBound();
            }
            if ( v.getUpperBound() < minUB ) {
                minUB = v.getUpperBound();
            }
        }
        for ( Variable v : this.variables ) {
            v.updateLowerBoundTo(maxLB);
            v.updateUpperBoundTo(minUB);
        }
    }
    
}
