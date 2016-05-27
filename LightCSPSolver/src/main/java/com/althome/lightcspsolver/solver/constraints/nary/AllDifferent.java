/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.constraints.nary;

import com.althome.lightcspsolver.solver.Sat;
import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.variables.Variable;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class AllDifferent implements Constraint {

    private ArrayList<Variable> variables;
    private ArrayList<Propagator> propagators;
    
    public AllDifferent(ArrayList<Variable> variables) {
        this.variables = variables;
        this.propagators = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Variable> getVariables() {
        return this.variables;
    }

    @Override
    public ArrayList<Propagator> getPropagators() {
        return this.propagators;
    }

    @Override
    public void filter() {
        for ( Propagator p : this.propagators ) {
            p.propagate();
        }
    }

    @Override
    public Sat isSatisfied() {
        int nbFixedVar = 0;
        for ( int i=0; i< this.variables.size(); i++ ) {            
            if ( this.variables.get(i).isInstantiated() ) {
                nbFixedVar++;
                for (int j=i+1; j<this.variables.size();j++) {
                    if ( this.variables.get(j).isInstantiated() && 
                         this.variables.get(j).getValue() == this.variables.get(i).getValue() ) {
                        return Sat.UNSAT;
                    }
                }
            }
        }
        if ( nbFixedVar == this.variables.size() ) {
            return Sat.SAT;
        }
        return Sat.IDK;
    }
    
}
