/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.search.selectors;

import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class InputOrderVariableSelector implements VariableSelector {

    @Override
    public Variable getVariable(ArrayList<Variable> variables) {
        for ( Variable v : variables ) {
            if ( !v.isInstantiated() && !v.isEmpty() ) {
                return v;
            }
        }
        return null;
    }
    
    
    
}
