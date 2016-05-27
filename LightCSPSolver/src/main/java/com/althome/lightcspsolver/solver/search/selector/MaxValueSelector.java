/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.search.selector;

import com.althome.lightcspsolver.solver.variables.Variable;

/**
 *
 * @author Arnaud
 */
public class MaxValueSelector implements ValueSelector {

    @Override
    public Integer getValue(Variable variable) {
        if ( variable.isEmpty() ) return null;
        else return variable.getUpperBound();
    }
    
}
