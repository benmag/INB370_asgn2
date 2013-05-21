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
	private Integer defaultSeats = 50;
	private Integer defaultPassengers = 30;
	private Integer invalidWeight = -100;
    private String defaultGoods = "G";
    private String invalidGoods = "K";
    private String defaultClassification = "4S";
    
    /**
     * Test the goods type is set correctly.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarType() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
        assertEquals(freight1.goodsType(), defaultGoods);
    }
    
    /**
     * Constructs a freight car with invalid goods type.
     * Should throw a train exception.
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidFreightCarType() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, invalidGoods);
    }
    
    /**
     * Constructs a freight car and tests it with valid weight.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarWeight() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	assertEquals(freight1.getGrossWeight(), defaultWeight);
    }
    
    /**
     * Constructs a freight car with an invalid weight.
     * Should through a train exception. 
     * This test passes.
     * @throws TrainException
     */
    @SuppressWarnings("unused")
    @Test (expected=TrainException.class)
    public void testInvalidFreightCarWeight() throws TrainException {
    	FreightCar freight1 = new FreightCar(invalidWeight, defaultGoods);
    }
    
    /**
     * 
     * @throws TrainException
     */
    @Test
    public void testLocomotivePower() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	Integer power = Integer.parseInt(defaultClassification.substring(0,1)) * 100;
    	assertEquals(locomotive1.power(), power);
    }
    
    @Test
    public void testLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	assertEquals(locomotive1.getGrossWeight(), defaultWeight);
    }
    
    
    @Test
    public void testPassengerCar() throws TrainException {
    	//Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	
    	
    	assertEquals(defaultPassengers, pass1.numberOnBoard());
    } 
}