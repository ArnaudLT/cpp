/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables.domains;

/**
 *
 * @author Arnaud
 */
public interface Domain {

    public void instantiateTo(int value);
    
    public void updateLowerBoundTo(int lb);
    
    public void updateUpperBoundTo(int ub);
    
    public void removeValues(int... values);
    
    public void removeValues(int minValue, int maxValue);
    
    public int getLowerBound();
    
    public int getUpperBound();

    public int getCardinality();
    
}
