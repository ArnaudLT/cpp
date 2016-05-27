/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables;

import com.althome.lightcspsolver.solver.Solver;

/**
 *
 * @author Arnaud
 */
public enum VariableFactory {
    ;
    
    public static Variable enumerated(String name, int lowerBound, int upperBound, Solver solver) {
        IntVariable v = new IntVariable(name, lowerBound, upperBound, DomainType.ENUMERATED);
        solver.addVariable(v);
        return v;
    }
    
    public static Variable bounded(String name, int lowerBound, int upperBound, Solver solver) {
        IntVariable v = new IntVariable(name, lowerBound, upperBound, DomainType.BOUNDED);
        solver.addVariable(v);
        return v;
    }
    
    
}
