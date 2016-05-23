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
public class EnumeratedIntDomainTest {
    
    public EnumeratedIntDomainTest() {
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
     * Test of instantiateTo method, of class EnumeratedIntDomain.
     */
    @Test
    public void testInstantiateTo() {
        System.out.println("instantiateTo");
        int value = 5;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);
        instance.instantiateTo(value);
        assertEquals(instance.getCardinality(), 1);
        assertEquals(instance.getLowerBound(), value);
    }

    /**
     * Test of updateLowerBoundTo method, of class EnumeratedIntDomain.
     */
    @Test
    public void testUpdateLowerBoundTo() {
        System.out.println("updateLowerBoundTo");
        int lb = 4;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);
        instance.updateLowerBoundTo(lb);
        assertEquals(instance.getLowerBound(), lb);
    }

    /**
     * Test of updateUpperBoundTo method, of class EnumeratedIntDomain.
     */
    @Test
    public void testUpdateUpperBoundTo() {
        System.out.println("updateUpperBoundTo");
        int ub = 5;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);
        instance.updateUpperBoundTo(ub);
        assertEquals(instance.getUpperBound(), ub);
    }

    /**
     * Test of removeValues method, of class EnumeratedIntDomain.
     */
    @Test
    public void testRemoveValues_intArr() {
        System.out.println("removeValues");
        int[] values = new int[]{2,4,5,8,9};
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);
        instance.removeValues(values);
        assertEquals(instance.getCardinality(), 3);
    }

    /**
     * Test of removeValues method, of class EnumeratedIntDomain.
     */
    @Test
    public void testRemoveValues_int_int() {
        System.out.println("removeValues");
        int minValue = 5;
        int maxValue = 10;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);
        instance.removeValues(minValue, maxValue);
        assertEquals(instance.getLowerBound(), 2);
        assertEquals(instance.getCardinality(), 3);
        assertEquals(instance.getUpperBound(), 4);
    }

    /**
     * Test of getLowerBound method, of class EnumeratedIntDomain.
     */
    @Test
    public void testGetLowerBound() {
        System.out.println("getLowerBound");
        int expResult = 2;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(expResult, 7);
        int result = instance.getLowerBound();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUpperBound method, of class EnumeratedIntDomain.
     */
    @Test
    public void testGetUpperBound() {
        System.out.println("getUpperBound");
        int expResult = 7;
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, expResult);
        int result = instance.getUpperBound();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardinality method, of class EnumeratedIntDomain.
     */
    @Test
    public void testGetCardinality() {
        System.out.println("getCardinality");
        EnumeratedIntDomain instance = new EnumeratedIntDomain(2, 7);;
        int result = instance.getCardinality();
        assertEquals(6, result);
    }
    
}
