/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver;

import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.search.Search;
import com.althome.lightcspsolver.solver.search.selector.InputOrderVariableSelector;
import com.althome.lightcspsolver.solver.search.selector.MinValueValueSelector;
import com.althome.lightcspsolver.solver.search.selector.ValueSelector;
import com.althome.lightcspsolver.solver.search.selector.VariableSelector;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.domains.Domain;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class Solver {
    
    private ArrayList<Variable> variables;
    private ArrayList<Constraint> constraints;
    
    private VariableSelector variableSelector;
    private ValueSelector valueSelector;
    
    
    public Solver() {
        this.variables = new ArrayList<>();
        this.constraints = new ArrayList<>();
        this.variableSelector = new InputOrderVariableSelector();
        this.valueSelector = new MinValueValueSelector();
    }
    
    public void addVariable(Variable variable) {
        this.variables.add(variable);
    }
    
    public void setVariableSelector(VariableSelector selector) {
        this.variableSelector = selector;
    }
    
    public void setValueSelector(ValueSelector selector) {
        this.valueSelector = selector;
    }
    
    public void post(Constraint constraint) {
        this.constraints.add(constraint);
    }
    
    public void solve() {
        solve(this);
    }
    
    
    private boolean solve(Solver s) {
        Sat status;
        s.filter();
        status = s.isASolution();
        if ( status == Sat.SAT ) return true;
        if ( status == Sat.UNSAT ) return false;
        
        // At least 1 variable is not instantiated.
        Variable nextVar;
        Integer nextValue;
        while ( ( nextVar = s.variableSelector.getVariable(s.variables) ) != null ) {
            while ( ( nextValue = s.valueSelector.getValue(nextVar) ) != null ) {
                Domain dom = nextVar.getDomain().clone();
                nextVar.instantiateTo(nextValue);
                System.out.println(nextVar);
                s.solve(s);
                status = s.isASolution();
                if ( status == Sat.SAT ) return true;
                nextVar.restoreDomain(dom);
                nextVar.removeValues(nextValue);
            }
        }
        
        return false;
    }
            
            
    private void filter() {
        for ( Constraint c : this.constraints ) {
            c.filter();
        }
    }
    
    private boolean isALeaf() {
        for ( Variable v : this.variables ) {
            if ( !v.isInstantiated() ) return false;
        }
        return true;
    }
    
    private Sat isASolution() {
        for ( Constraint c : this.constraints ) {
            Sat cIsSat = c.isSatisfied();
            if ( cIsSat == Sat.UNSAT ) return Sat.UNSAT;
            if ( cIsSat == Sat.IDK ) return Sat.IDK;
        }
        return Sat.SAT;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(" ------------------------------ \n");
        Sat satified = this.isASolution();
        if ( satified == Sat.SAT ) s.append(" SAT ");
        else if ( satified == Sat.UNSAT ) s.append(" UNSAT ");
        else s.append(" IDK ");
        s.append("\n");
        for ( Variable v : this.variables ) {
            s.append(v).append("\n");            
        }
        s.append(" ------------------------------ ");
        return s.toString();
    }
    
}

