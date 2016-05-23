/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.samples;

import com.althome.lightcspsolver.solver.constraints.Equal;
import com.althome.lightcspsolver.solver.constraints.propagators.PropEqualBC;
import com.althome.lightcspsolver.solver.variables.DomainType;
import com.althome.lightcspsolver.solver.variables.IntVariable;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.domains.EnumeratedIntDomain;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class Manual {
    
    public static void main(String[] args) {
        /*
        IntVariable var1 = new IntVariable(1, "v1", 5, 19, DomainType.BOUNDED);
        System.out.println(var1);
        
        System.out.println("var1.updateLowerBoundTo(12);");
        var1.updateLowerBoundTo(12);
        System.out.println(var1);
        
        System.out.println("var1.updateUpperBoundTo(16);");
        var1.updateUpperBoundTo(16);
        System.out.println(var1);
        
        System.out.println("var1.removeValues(13, 15);");
        var1.removeValues(13, 15);
        System.out.println(var1);
        */
        
        
        ArrayList<Variable> vars = new ArrayList<>();
        vars.add(new IntVariable(0, "v1", 1, 12, DomainType.ENUMERATED));
        vars.add(new IntVariable(1, "v2", 2, 11, DomainType.ENUMERATED));
        vars.add(new IntVariable(2, "v3", 3, 10, DomainType.ENUMERATED));
        vars.add(new IntVariable(3, "v4", 5, 15, DomainType.ENUMERATED));
        vars.add(new IntVariable(4, "v5", 7, 7, DomainType.ENUMERATED));
        
        Equal eq = new Equal(vars);
        
        for (Variable v : vars) {
            System.out.println(v);
        }
        System.out.println("=> propagation <=");
        eq.filter();
        
        for (Variable v : vars) {
            System.out.println(v);
        }
        
        

    }
    
}
