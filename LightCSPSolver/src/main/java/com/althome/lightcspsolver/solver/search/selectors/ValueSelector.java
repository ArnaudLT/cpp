/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.search.selectors;

import com.althome.lightcspsolver.solver.variables.Variable;

/**
 *
 * @author Arnaud
 */
public interface ValueSelector {
 
    public Integer getValue(Variable variable);
    
}
