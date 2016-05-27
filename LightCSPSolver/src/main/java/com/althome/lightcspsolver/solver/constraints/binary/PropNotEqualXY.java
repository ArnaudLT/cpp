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
public class PropNotEqualXY implements Propagator {

    
    private Variable x, y;
    
    public PropNotEqualXY(ArrayList<Variable> variables) {
        this.x = variables.get(0);
        this.y = variables.get(1);
    }
    
    
    @Override
    public boolean propagate() {

        int deltaBefore = this.x.getCardinality() + this.x.getCardinality();
        if ( x.isInstantiated() ) {
            y.removeValues(x.getValue());
        }
        if ( y.isInstantiated() ) {
            x.removeValues(y.getValue());
        }
        int deltaAfter = this.x.getCardinality() + this.x.getCardinality(); 
        
        return deltaAfter != deltaBefore;
    }
    
    
    
    
}
