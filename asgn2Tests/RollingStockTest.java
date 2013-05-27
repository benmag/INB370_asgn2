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
	private Integer defaultWeight = 50; 	//Default weight for each carriage.
	private Integer defaultSeats = 50;  	//Default seats for passenger carriages.
	private Integer defaultPassengers = 30; //Default number of boarding passengers.
	private Integer tooManyPassengers = defaultSeats + 100; //Too many passengers boarding
	private Integer invalidPassengers = -30;//Negative boarding passengers
	private Integer defaultAlightingPassengers = 10;		//default number of passengers leaving
	private Integer invalidAlightingPassengers = -10;		//invalid number of passengers leaving
	private Integer tooManyAlightingPassengers = defaultPassengers + 10;	//too many passengers leaving
	private Integer invalidSeats = -100;	//Invalid number of seats
	private Integer invalidWeight = -100;	//Invalid weight
	private Integer ZERO = 0;				//Zero - used for zero weight, zero seats, etc. 
    private String generalGoods = "G";		//General goods type for freight cars.
    private String dangerousGoods = "D";	//Dangerous goods type for freight cars.
    private String refrigeratedGoods = "R"; //Refrigerated goods type for freight cars.
    private String invalidGoods = "K";		//Invalid goods type for freight cars
    private String returnString = "Freight("+generalGoods+")";	//The default return string for freight cars
    private String defaultClassification = "4S"; //The default classification for locomotives
    private String invalidPower = "12S";		 //Invalid power (with valid engine type) for locomotives.
    private String invalidEngineType = "7P";	 //Invalid engine type (with valid power) for locomotives.
    private String invalidPowerAndEngine = "13K";//Invalid engine type and power for locomotives.
    private String locoReturnString = "Loco("+defaultClassification+")";	//Default return string for locomotives
    private String passReturnString = "Passenger("+defaultPassengers+"/"+defaultSeats+")"; //Default return string for passenger cars.
    
    /**
     * Test the general goods type is set correctly.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarTypeGeneral() throws TrainException {
    	FreightCar freight1 = new FreightCar(defaultWeight, generalGoods);
        assertEquals(freight1.goodsType(), generalGoods);
    }
    
    /**
     * Tests the Dangerous goods type is set correctly.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarTypeDangerous() throws TrainException {
    	FreightCar freight2 = new FreightCar(defaultWeight, dangerousGoods);
    	assertEquals(freight2.goodsType(), dangerousGoods);
    }
    
    /**
     * Tests the Refrigerated goods type is set correctly.
     * This test passes.
     * @throws TrainException
     */
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
    
    /**
     * Constructs a freight car with a weight of zero. 
     * Should throw a train exception (weight must be positive).
     * This test passes.
     * @throws TrainException
     */
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
    
    /**
     * Tests the string output of FreightCar works as it should. 
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testFreightCarToString() throws TrainException {
    	FreightCar freight = new FreightCar(defaultWeight, generalGoods);
    	assertEquals(freight.toString(), returnString);
    }
    
    /**
     * Tests the power returned by a Locomotive.
     * This test passes.
     * @throws TrainException
     */ 
    @Test
    public void testLocomotivePower() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	Integer power = Integer.parseInt(defaultClassification.substring(0,1)) * 100;
    	assertEquals(locomotive1.power(), power);
    }
    
    /**
     * Constructs a locomotive with valid weight, and confirms the weight.
     * This test passes. 
     * @throws TrainException
     */
    @Test
    public void testLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	assertEquals(locomotive1.getGrossWeight(), defaultWeight);
    }
    
    /**
     * Constructs a locomotive with zero weight.
     * Should throw a train exception, weight must be positive.
     * This test passes.
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testZeroLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(ZERO, defaultClassification);
    }
    
    /**
     * Constructs a locomotive with invalid (negative) weight.
     * Should throw a train exception, weight must be positive.
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotiveWeight() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(invalidWeight, defaultClassification);
    }
    
    /**
     * Constructs a locomotive with invalid power (not between 1-9).
     * Should throw a train exception, power must be between 1 and 9.
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotivePower() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidPower);
    }
    
    /**
     * Constructs a locomotive with invalid engine type (E, D, or S).
     * Should throw a train exception, engine type must be E, D, or S.
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotiveEngineType() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidEngineType);
    }
    
    /**
     * Constructs a locomotive with invalid power & engine type.
     * Should throw a train exception, power must be between 1 and 9, engine must be E, D, or S. 
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidLocomotivePowerAndEngine() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, invalidPowerAndEngine);
    }
    
    /**
     * Tests the return string of Locomotive works as it should.
     * This test passes. 
     * @throws TrainException
     */
    @Test
    public void testLocomotiveToString() throws TrainException {
    	Locomotive locomotive1 = new Locomotive(defaultWeight, defaultClassification);
    	assertEquals(locomotive1.toString(), locoReturnString);
    }
    
    /**
     * Constructs a passenger car with a valid weight, then tests the weight.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	assertEquals(pass1.getGrossWeight(), defaultWeight);
    }
    
    /**
     * Constructs a passenger car with a weight of zero.
     * Should throw a train exception (weight must be positive).
     * This test passes.
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testZeroPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(ZERO, defaultSeats);
    }
    
    /**
     * Constructs a passenger car with an invalid weight.
     * Should throw a train exception (weight must be positive).
     * This test passes.
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidPassengerCarWeight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(invalidWeight, defaultSeats);
    }
    
    /**
     * Constructs a passengercar and tests the number of seats are set correctly.
     * This test passes. 
     * @throws TrainException
     */
    @Test
    public void testPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	assertEquals(pass1.numberOfSeats(), defaultSeats);
    }
    
    /**
     * Constructs a passenger car with an invalid number of seats.
     * Should throw a train exception (number of seats must be positive).
     * This test passes. 
     * @throws TrainException
     */
    @SuppressWarnings("unused")
	@Test (expected=TrainException.class)
    public void testInvalidPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, invalidSeats);
    }
    
    /**
     * Constructs a passenger car with 0 seats.
     * This test passes. 
     * @throws TrainException
     */
    @Test
    public void testZeroPassengerSeats() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, ZERO);
    	assertEquals(pass1.numberOfSeats(), ZERO);
    }
    
    /**
     * Constructs a passenger car and boards passengers, then checks the same
     * number of passengers are on board.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testPassengerBoard() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	assertEquals(pass1.numberOnBoard(), defaultPassengers);
    }
    
    /**
     * Constructs a passenger car, boards an invalid number of passengers.
     * Should throw a train exception (number of passengers must be positive). 
     * This test passes.
     * @throws TrainException
     */
    @Test (expected=TrainException.class)
    public void testInvalidPassengerBoard() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(invalidPassengers);
    }
    
    /**
     * Constructs a passenger car and boards more passengers than the number of seats.
     * Should seat all passengers, with leftovers (no errors). 
     * This test passes. 
     * @throws TrainException
     */
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
    
    /**
     * Constructs a passenger car, boards passengers, then alights some passengers.
     * Checks the number on board was the original minus those who left.
     * This test passes. 
     * @throws TrainException
     */
    @Test
    public void testPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(defaultAlightingPassengers);
    	Integer onBoard = defaultPassengers - defaultAlightingPassengers;
    	assertEquals(pass1.numberOnBoard(), onBoard);
    }
    
    /**
     * Constructs a passenger car, boards passengers, then alights an invalid amount of passengers.
     * Should throw a train exception - cannot remove a negative number of passengers. 
     * This test passes. 
     * @throws TrainException
     */
    @Test (expected=TrainException.class)
    public void testInvalidPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(invalidAlightingPassengers);
    }
    
    /**
     * Constructs a passenger car, boards passengers, then alights an too many passengers.
     * Should throw a train exception - cannot remove more passengers than there are. 
     * This test passes. 
     * @throws TrainException
     */
    @Test (expected=TrainException.class)
    public void testTooManyPassengerAlight() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	pass1.alight(tooManyAlightingPassengers);
    }
    
    /**
     * Tests the toString method works as it should.
     * This test passes.
     * @throws TrainException
     */
    @Test
    public void testPassengerToString() throws TrainException {
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	pass1.board(defaultPassengers);
    	assertEquals(pass1.toString(), passReturnString);
    }
}