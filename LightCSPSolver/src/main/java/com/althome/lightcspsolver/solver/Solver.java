/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver;

import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.search.selectors.InputOrderVariableSelector;
import com.althome.lightcspsolver.solver.search.selectors.MaxValueSelector;
import com.althome.lightcspsolver.solver.search.selectors.MinValueSelector;
import com.althome.lightcspsolver.solver.search.selectors.ValueSelector;
import com.althome.lightcspsolver.solver.search.selectors.VariableSelector;
import com.althome.lightcspsolver.solver.utils.Pair;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.domains.Domain;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        this.valueSelector = new MinValueSelector();
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
    
    public void post(Constraint... constraints) {
        for (Constraint c : constraints) {
            this.constraints.add(c);
            for (Variable v : c.getVariables()) {
                v.addConstraint(c);
            }
        }
    }
    
    public Sat solve() {
        return this.solve(null, 0);
    }
    
    private Sat solve(Variable move, int depth) {
//StringBuilder s = new StringBuilder();
//for (int i=0; i<depth; i++) { s.append("  "); }
//System.out.println(s.toString()+this.toStringOneLine());
        this.brainlessPropagation();
        //this.brainlessPropagation();
//System.out.println(s.toString()+"Propag => "+this.toStringOneLine());
        Sat status = this.isASolution();
        if (status == Sat.SAT) { /*System.out.println(this.toStringOneLine());*/ return Sat.SAT; }
        if (status == Sat.UNSAT) { return Sat.UNSAT; }
        Variable var;
        Integer value;
        depth++;
        if ((var = this.variableSelector.getVariable(this.variables)) != null) {
            Domain backtrackPrevVar = var.getDomain().clone();
            while ((value = this.valueSelector.getValue(var)) != null) {
                ArrayList<Domain> backup = this.pushWorld();
                var.instantiateTo(value);
//System.out.println(s.toString()+var);                
                Sat solved = this.solve(var, depth);
                if (solved == Sat.SAT) {
                    return Sat.SAT;
                }
                this.popWorld(backup);
                var.removeValues(value);
            }
            var.restoreDomain(backtrackPrevVar);
        }
        return Sat.UNSAT;
    }
    
    private void brainlessPropagation() {
        boolean impact;
        do {
            impact = false;
            for ( Constraint c : this.constraints ) {
                impact |= c.filter();
            }
        } while (impact);
    }
    
    private void aBetterPropagation() {
        ArrayList<Propagator> queue = new ArrayList<Propagator>();
        
        
    }
    
    private ArrayList<Domain> pushWorld() {
        ArrayList<Domain> backup = new ArrayList<Domain>(this.variables.size());
        for ( Variable v : this.variables ) {
            backup.add(v.getDomain().clone());
        }
        return backup;
    }
    
    private void popWorld(ArrayList<Domain> backup) {
        for ( int i=0; i< this.variables.size(); i++ ) {
            this.variables.get(i).
                    restoreDomain(backup.get(i));
        }
        //backup.clear();
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
    
    public String toStringOneLine() {
        StringBuilder s = new StringBuilder();
        Sat satified = this.isASolution();
        s.append(satified).append(" =>>> ");
        for ( Variable v : this.variables ) {
            s.append(v).append(" // ");            
        }
        return s.toString();
    }
    
}

