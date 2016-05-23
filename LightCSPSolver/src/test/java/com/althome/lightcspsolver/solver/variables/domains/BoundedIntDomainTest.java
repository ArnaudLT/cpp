/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.solver.variables.domains;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arnaud
 */
public class BoundedIntDomainTest {
    
    public BoundedIntDomainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of instantiateTo method, of class BoundedIntDomain.
     */
    @Test
    public void testInstantiateTo() {
        System.out.println("instantiateTo");
        int value = 5;
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        instance.instantiateTo(value);
        assertEquals(instance.getCardinality(), 1);
        assertEquals(instance.getLowerBound(), value);
    }

    /**
     * Test of updateLowerBoundTo method, of class BoundedIntDomain.
     */
    @Test
    public void testUpdateLowerBoundTo() {
        System.out.println("updateLowerBoundTo");
        int lb = 4;
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        instance.updateLowerBoundTo(lb);
        assertEquals(instance.getLowerBound(), lb);
    }

    /**
     * Test of updateUpperBoundTo method, of class BoundedIntDomain.
     */
    @Test
    public void testUpdateUpperBoundTo() {
        System.out.println("updateUpperBoundTo");
        int ub = 5;
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        instance.updateUpperBoundTo(ub);
        assertEquals(instance.getUpperBound(), ub);
    }

    /**
     * Test of removeValues method, of class BoundedIntDomain.
     */
    @Test
    public void testRemoveValues_intArr() {
        System.out.println("removeValues");
        int[] values = new int[]{7,2,3,6};
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        instance.removeValues(values);
        assertEquals(instance.getLowerBound(), 4);
        assertEquals(instance.getUpperBound(), 5);
    }

    /**
     * Test of removeValues method, of class BoundedIntDomain.
     */
    @Test
    public void testRemoveValues_int_int() {
        System.out.println("removeValues");
        int minValue = 5;
        int maxValue = 12;
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        instance.removeValues(minValue, maxValue);
        assertEquals(instance.getLowerBound(), 2);
        assertEquals(instance.getCardinality(), 3);
    }

    /**
     * Test of getLowerBound method, of class BoundedIntDomain.
     */
    @Test
    public void testGetLowerBound() {
        System.out.println("getLowerBound");
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        int expResult = 2;
        int result = instance.getLowerBound();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getUpperBound method, of class BoundedIntDomain.
     */
    @Test
    public void testGetUpperBound() {
        System.out.println("getUpperBound");
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        int expResult = 7;
        int result = instance.getUpperBound();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardinality method, of class BoundedIntDomain.
     */
    @Test
    public void testGetCardinality() {
        System.out.println("getCardinality");
        BoundedIntDomain instance = new BoundedIntDomain(2, 7);
        int expResult = 6;
        int result = instance.getCardinality();
        assertEquals(expResult, result);
    }
    
}
