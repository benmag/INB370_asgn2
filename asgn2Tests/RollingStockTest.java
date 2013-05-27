/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asgn2Tests;


import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import static org.junit.Assert.*;

/**
 *
 * @author Spike
 */
public class RollingStockTest {
	private Integer defaultWeight = 50;
	private Integer defaultSeats = 50;
	private Integer defaultPassengers = 30;
	private Integer tooManyPassengers = defaultSeats + 100;
	private Integer invalidPassengers = -30;
	private Integer defaultAlightingPassengers = 10;
	private Integer invalidAlightingPassengers = -10;
	private Integer tooManyAlightingPassengers = defaultPassengers + 10;
	private Integer invalidSeats = -100;
	private Integer invalidWeight = -100;
	private Integer ZERO = 0;
    private String generalGoods = "G";
    private String dangerousGoods = "D";
    private String refrigeratedGoods = "R";
    private String invalidGoods = "K";
    private String returnString = "Freight("+generalGoods+")";
    private String defaultClassification = "4S";
    private String invalidPower = "12S";
    private String invalidEngineType = "7P";
    private String invalidPowerAndEngine = "13K";
    private String locoReturnString = "Loco("+defaultClassification+")";
    private String passReturnString = "Passenger("+defaultPassengers+"/"+defaultSeats+")";
    
    /**
     * Test the goods type is set correctly.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarTypeGeneral() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, generalGoods);
        assertEquals(freight1.goodsType(), generalGoods);
    }
    
    @Test
    public void testFreightCarTypeDangerous() throws TrainException {
    	FreightCar freight2 = new FreightCar(defaultWeight, dangerousGoods);
    	assertEquals(freight2.goodsType(), dangerousGoods);
    }
    
    @Test
    public void testFreightCarTypeRefrigerated() throws TrainException {
    	FreightCar freight2 = new FreightCar(defaultWeight, refrigeratedGoods);
    	assertEquals(freight2.goodsType(), refrigeratedGoods);
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
    	FreightCar freight1 = new FreightCar(defaultWeight, generalGoods);
    	assertEquals(freight1.getGrossWeight(), defaultWeight);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testFreightCarWeightZero() throws TrainException {
    	FreightCar freight1 = new FreightCar(ZERO, generalGoods);
    }
    
    /**
     * Constructs a freight car with an invalid weight.
     * Should throw a train exception. 
     * This test passes.
     * @throws TrainException
     */
    @SuppressWarnings("unused")
    @Test (expected=TrainException.class)
    public void testInvalidFreightCarWeight() throws TrainException {
    	FreightCar freight1 = new FreightCar(invalidWeight, generalGoods);
    }
    
    @Test
    public void testFreightCarToString() throws TrainException {
    	FreightCar freight = new FreightCar(defaultWeight, generalGoods);
    	assertEquals(freight.toString(), returnString);
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
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testZeroLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(ZERO, defaultClassification);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(invalidWeight, defaultClassification);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotivePower() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidPower);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotiveEngineType() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidEngineType);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotivePowerAndEngine() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidPowerAndEngine);
    }
    
    @Test
    public void testLocomotiveToString() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	assertEquals(locomotive1.toString(), locoReturnString);
    }
    
    
    @Test
    public void testPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	assertEquals(pass1.getGrossWeight(), defaultWeight);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testZeroPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(ZERO, defaultSeats);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(invalidWeight, defaultSeats);
    }
    
    @Test
    public void testPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	assertEquals(pass1.numberOfSeats(), defaultSeats);
    }
    
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, invalidSeats);
    }
    
    @Test
    public void testZeroPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, ZERO);
    	assertEquals(pass1.numberOfSeats(), ZERO);
    }
    
    @Test
    public void testPassengerBoard() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	assertEquals(pass1.numberOnBoard(), defaultPassengers);
    }
    
    @Test (expected=TrainException.class)
    public void testInvalidPassengerBoard() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(invalidPassengers);
    }
    
    @Test
    public void testPassengerBoardTooMany() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(tooManyPassengers);
    	assertEquals(pass1.numberOnBoard(), defaultSeats); //Should board everyone that will fit
    }
    
    /**
     * Checks the remaining passengers that could not board is equal to the number attempting
     * to board minus the number that got on board.
     * @throws TrainException
     */
    @Test
    public void testPassengerBoardLeftover() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	Integer leftover = pass1.board(tooManyPassengers);
    	Integer calculateLeftover = (tooManyPassengers - defaultSeats);
    	assertEquals(leftover, calculateLeftover); 
    }
    
    @Test
    public void testPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(defaultAlightingPassengers);
    	Integer onBoard = defaultPassengers - defaultAlightingPassengers;
    	assertEquals(pass1.numberOnBoard(), onBoard);
    }
    
    @Test (expected=TrainException.class)
    public void testInvalidPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(invalidAlightingPassengers);
    }
    
    @Test (expected=TrainException.class)
    public void testTooManyPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(tooManyAlightingPassengers);
    }
    
    @Test
    public void testPassengerToString() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	assertEquals(pass1.toString(), passReturnString);
    }
}