/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables.domains;

import java.util.BitSet;

/**
 *
 * @author Arnaud
 */
public class EnumeratedIntDomain implements Domain {
    
    private BitSet values;
    private int offset;
    
    public EnumeratedIntDomain(int minValue, int maxValue) {
        this.offset = minValue;
        this.values = new BitSet(maxValue-minValue+1);
        this.values.set(0,maxValue-minValue+1);
    }

    private EnumeratedIntDomain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void instantiateTo(int value) {
        this.values.clear();
        this.values.set(value-this.offset, true);
    }
    
    @Override
    public void updateLowerBoundTo(int lb) {
        if ( this.isAValidValue(lb) ) {
            this.values.set(0, lb - this.offset, false);
        }
    }

    @Override
    public void updateUpperBoundTo(int ub) {
        if ( this.isAValidValue(ub) ) {
            this.values.set(ub - this.offset + 1, this.values.size(), false);
        }
    }
    
    @Override
    public void removeValues(int... values) {
        for ( int v : values ) {
            if ( this.isAValidValue(v) ) this.values.set(v-this.offset, false);
        }
    }

    @Override
    public void removeValues(int minValue, int maxValue) {
        if ( minValue <= this.getLowerBound() ) {
            this.updateLowerBoundTo(maxValue+1);
        } else if ( maxValue >= this.getUpperBound() ) {
            this.updateUpperBoundTo(minValue-1);
        } else {
            this.values.set(minValue - this.offset, maxValue + 1 - this.offset, false);
        }
    }
    
    @Override
    public int getLowerBound() {
        return this.values.nextSetBit(0) + this.offset;
    }
    
    @Override
    public int getUpperBound() {
        return this.values.previousSetBit(this.values.size()-1) + this.offset;
    }
    
    @Override
    public int getCardinality() {
        return this.values.cardinality();
    }
    
    @Override
    public Domain clone() {
        EnumeratedIntDomain clone = new EnumeratedIntDomain();
        clone.offset = this.offset;
        clone.values = (BitSet) this.values.clone();
        return clone;
    }
    
    private boolean isAValidValue(int v) {
        return ( v - this.offset >= 0 && v - this.offset < this.values.size() );
    }
    
    public String toString() {
        StringBuilder dom = new StringBuilder("{ ");
        for (int i=0; i<this.values.size(); i++) {
            if ( this.values.get(i) ) {
                dom.append(i+this.offset);
                dom.append(" ");
            }
        }
        dom.append("}");
        return dom.toString();
    }

}
