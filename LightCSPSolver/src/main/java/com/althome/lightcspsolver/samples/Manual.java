/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.samples;

import com.althome.lightcspsolver.solver.*;
import com.althome.lightcspsolver.solver.constraints.ConstraintFactory;
import com.althome.lightcspsolver.solver.variables.*;
import java.util.ArrayList;


/**
 *
 * @author Arnaud
 */
public class Manual {
    
    public static void main(String[] args) {

        Solver s = new Solver();
        
        int size = 100;
        ArrayList<Variable> vars = new ArrayList<Variable>();
        for (int i=0; i<size; i++) {
            vars.add(VariableFactory.enumerated("v"+i, 0, size-1, s));
        }
                      
        for (int i=1; i<size; i++) {
            // Mauvais modele !
            //s.post( ConstraintFactory.arith(vars.get(i-1), ">=", vars.get(i)) );
            //s.post( ConstraintFactory.arith(vars.get(i-1), "!=", vars.get(i)) );
            // Bon modele !
            s.post( ConstraintFactory.arith(vars.get(i-1), ">", vars.get(i)) );
        }
        /*
        final int SIZE = 9;
        ArrayList<ArrayList<Variable>> grid = new ArrayList<>();
        ArrayList<Variable> line, col;
        
        // Build variables
        for (int i=0; i<SIZE; i++) {
            line = new ArrayList<>();
            for (int j=0; j<SIZE; j++) {          
                line.add(VariableFactory.enumerated(i+"_"+j, 0, SIZE, s));
            }
            grid.add(line);
        }
        
        // Build and post constraints
        for (int i=0; i<SIZE; i++) {
            s.post(new AllDifferent(grid.get(i)));
            col = new ArrayList<Variable>();
            for (int j=0; j<SIZE; j++) { 
                col.add(grid.get(j).get(i));
            }
            s.post(new AllDifferent(col));
        }
        */
        
        
        
        long start, duration, end;
        start = System.currentTimeMillis();
        
        System.out.println(s.solve());
        
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.print("time = "+duration+" ms");
    }
    
}
