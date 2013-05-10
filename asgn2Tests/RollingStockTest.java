/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asgn2Tests;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Spike
 */
public class RollingStockTest {
    private Integer defaultWeight = 50;
    private String defaultGoods = "G";
    
    @Test
    public void testFreightCarType() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
        assertEquals(freight1.goodsType(), defaultGoods);
    }
    
    @Test
    public void testFreightCarWeight() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	assertEquals(freight1.getGrossWeight(), defaultWeight);
    }
    
    @Test
    public void testLocomotiveType() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification)
    
    }
}