/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asgn2Tests;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Spike
 */
public class RollingStockTest {
    int defaultWeight = 50;
    
    public static void main(String args[]) {
        
    }
    
    @Test
    public void testFreightCarType() throws TrainException {
	FreightCar freight1 = new FreightCar(defaultWeight, "G");
        assertEquals(freight1.goodsType(), "G");
        if("G".equals(freight1.goodsType())) {
            System.out.println("Freight1 goods is correct.");
        } else {
            System.out.println("Freight1 goods is incorrect: " + freight1.goodsType());
        }
    }
}