/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints.binary;

import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class PropEqualXY implements Propagator {

    
    private Variable x, y;
    
    public PropEqualXY(ArrayList<Variable> variables) {
        this.x = variables.get(0);
        this.y = variables.get(1);
    }
    
    
    @Override
    public boolean propagate() {
        
        int deltaBefore = this.x.getCardinality() + this.x.getCardinality();
        
        int ub = this.x.getUpperBound();
        for (int xv = this.x.getLowerBound(); xv <= ub; xv = this.x.nextValue(xv)) {
            if ( ! this.y.contains(xv) ) {
                this.x.removeValues(xv);
            }
        }
        
        ub = this.y.getUpperBound();
        for (int yv = this.y.getLowerBound(); yv <= ub; yv = this.y.nextValue(yv)) {
            if ( ! this.x.contains(yv) ) {
                this.y.removeValues(yv);
            }
        }
        
        int deltaAfter = this.x.getCardinality() + this.x.getCardinality(); 
        
        return (deltaBefore != deltaAfter);
    }
    
    
    
    
}
