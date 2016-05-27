/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables;

import com.althome.lightcspsolver.solver.constraints.Constraint;
import com.althome.lightcspsolver.solver.variables.domains.BoundedIntDomain;
import com.althome.lightcspsolver.solver.variables.domains.EnumeratedIntDomain;
import com.althome.lightcspsolver.solver.variables.domains.Domain;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class IntVariable implements Variable {
    
    private String name;
    private Domain domain;
    private ArrayList<Constraint> constaints;
    
    private IntVariable() {
        
    }
    
    public IntVariable(String name, int minValue, int maxValue, DomainType type) {
        this.name = name;
        switch (type) {
            case ENUMERATED : 
                this.domain = new EnumeratedIntDomain(minValue, maxValue);
                break;
            case BOUNDED :
                this.domain = new BoundedIntDomain(minValue, maxValue);
                break;
            default :
                this.domain = new EnumeratedIntDomain(minValue, maxValue);
        }
        this.constaints = new ArrayList<>();
    }
    
    @Override
    public void instantiateTo(int value) {
        this.domain.instantiateTo(value);
    }
    
    @Override
    public boolean isInstantiated() {
        return this.domain.getCardinality() == 1;
    }
    
    @Override
    public void updateLowerBoundTo(int lb) {
        this.domain.updateLowerBoundTo(lb);
    }

    @Override
    public void updateUpperBoundTo(int ub) {
        this.domain.updateUpperBoundTo(ub);
    }
    
    @Override
    public void removeValues(int... values) {
        this.domain.removeValues(values);
    }

    @Override
    public void removeValues(int minValue, int maxValue) {
        this.domain.removeValues(minValue, maxValue);
    }

    @Override
    public int getLowerBound() {
        return this.domain.getLowerBound();
    }

    @Override
    public int getUpperBound() {
        return this.domain.getUpperBound();
    }
    
    @Override
    public int getValue() {
        assert ( this.isInstantiated() );
        return this.domain.getLowerBound();
    }
    
    @Override    
    public int getCardinality() {
        return this.domain.getCardinality();
    }
    
    @Override
    public Domain getDomain() {
        return this.domain;
    }
    
    @Override
    public void addConstraint(Constraint c) {
        this.constaints.add(c);
    }
    
    @Override
    public IntVariable clone() {
        IntVariable clone = new IntVariable();
        clone.name = this.name;
        clone.constaints = this.constaints;
        clone.domain = this.domain.clone();
        return clone;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        StringBuilder dom = new StringBuilder("[ ");
        dom.append(this.name);
        dom.append(" <- ");
        dom.append(this.domain);
        dom.append(" ]");
        return dom.toString();
    } 

    @Override
    public void restoreDomain(Domain d) {
        this.domain = d;
    }

    @Override
    public boolean isEmpty() {
        return this.domain.isEmpty();
    }
    
}
