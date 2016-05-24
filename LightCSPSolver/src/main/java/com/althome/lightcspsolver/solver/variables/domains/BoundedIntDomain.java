/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables.domains;

import java.util.Arrays;


/**
 *
 * @author Arnaud
 */
public class BoundedIntDomain implements Domain {
    
    private int lowerBound;
    private int upperBound;
    
    public BoundedIntDomain(int minValue, int maxValue) {
        this.lowerBound = minValue;
        this.upperBound = maxValue;
    }
    
    @Override
    public void instantiateTo(int value) {
        this.lowerBound = value;
        this.upperBound = value;
    }
    
    @Override
    public void updateLowerBoundTo(int lb) {
        if ( lb > this.lowerBound ) {
            this.lowerBound = lb;
        }
    }

    @Override
    public void updateUpperBoundTo(int ub) {
        if ( ub < this.upperBound ) {
            this.upperBound = ub;
        }
    }
    
    @Override
    public void removeValues(int... values) {
        Arrays.sort(values);
        for ( int i=0; i < values.length; i++ ) {
            if ( values[i] == this.lowerBound ) {
                this.lowerBound++;
            }
        }
        for ( int i=values.length-1; i >= 0; i-- ) {
            if ( values[i] == this.upperBound ) {
                this.upperBound--;
            }
        }
    }

    @Override
    public void removeValues(int minValue, int maxValue) {
        if ( minValue <= this.lowerBound ) {
            this.updateLowerBoundTo(maxValue+1);
        } else if ( maxValue >= this.upperBound ) {
            this.updateUpperBoundTo(minValue-1);
        }
    }
    
    @Override
    public int getLowerBound() {
        return this.lowerBound;
    }
    
    @Override
    public int getUpperBound() {
        return this.upperBound;
    }
    
    @Override
    public int getCardinality() {
        return Math.max(0, this.upperBound - this.lowerBound + 1);
    }
    
    @Override
    public Domain clone() {
        return new BoundedIntDomain(this.lowerBound, this.lowerBound);
    }
    
    public String toString() {
        StringBuilder dom = new StringBuilder("[ ");
        dom.append(this.lowerBound).append(",").append(this.upperBound);
        dom.append("]");
        return dom.toString();
    }

}
