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
public class PropLessOrEqualXY implements Propagator {

    
    private Variable x, y;
    
    public PropLessOrEqualXY(ArrayList<Variable> variables) {
        this.x = variables.get(0);
        this.y = variables.get(1);
    }
    
    
    @Override
    public boolean propagate() {
 
        int deltaBefore = this.x.getCardinality() + this.y.getCardinality();

        this.x.updateUpperBoundTo(this.y.getUpperBound());
        this.y.updateLowerBoundTo(this.x.getLowerBound());
            
        int deltaAfter = this.x.getCardinality() + this.y.getCardinality(); 
        
        return deltaBefore != deltaAfter;
    }
    
    
    
    
}
