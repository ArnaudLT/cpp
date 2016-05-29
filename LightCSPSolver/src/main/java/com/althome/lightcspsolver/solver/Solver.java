/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver;

import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.constraints.Propagator;
import com.althome.lightcspsolver.solver.search.selector.InputOrderVariableSelector;
import com.althome.lightcspsolver.solver.search.selector.MaxValueSelector;
import com.althome.lightcspsolver.solver.search.selector.MinValueSelector;
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
        this.initialPropagation();
        return this.solve(0, null);
    }
    
    private Sat solve(int dig, Variable vMove) {
StringBuilder s = new StringBuilder();
//for (int i=0; i<dig; i++) { s.append("  "); }
//System.out.println(s.toString()+this.toStringOneLine());
        this.AC3Propagation(vMove);
        //this.initialPropagation();
//System.out.println(s.toString()+"Propag => "+this.toStringOneLine());
        Sat status = this.isASolution();
        if (status == Sat.SAT) {
System.out.println(this.toStringOneLine());
            return Sat.SAT;
        }
        if (status == Sat.UNSAT) {
            return Sat.UNSAT;
        }
        Variable var;
        Integer value;
        dig++;
        if ((var = this.variableSelector.getVariable(this.variables)) != null) {
            Domain backtrackPrevVar = var.getDomain().clone();
            while ((value = this.valueSelector.getValue(var)) != null) {
                ArrayList<Domain> backup = this.pushWorld();
                var.instantiateTo(value);
//System.out.println(s.toString()+var);                
                Sat solved = this.solve(dig, var);
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
    }
            
    private void initialPropagation() {
        boolean impact;
        do {
            impact = false;
            for ( Constraint c : this.constraints ) {
                impact |= c.filter();
            }
        } while (impact);
        
        
    }
    
    // P.O.C.
    //   - cstrList => doublons !
    //   - 
    private void AC3Propagation(Variable vMove) {
        Constraint cstr = null;
        ArrayList<Constraint> cstrList = new ArrayList<>(this.constraints.size());
        // initialisation
        if ( vMove != null ) cstrList.addAll(vMove.getConstraints());
        while ( !cstrList.isEmpty() ) {
            cstr = cstrList.remove(0);
            if ( cstr.filter() ) {
                // TODO check empty possible domain
                for (Variable vj : cstr.getVariables()) {
                    if ( vj != vMove ) {
                        for (Constraint cj : vj.getConstraints() ) {
                            if ( cj != cstr ) {
                                cstrList.add(cj);
                            }
                        }
                    }
                }
            }
            
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

