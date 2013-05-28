/*
 * TrainTests
 * 
 * @version 1.0
 * @author Ben Maggacis, Corey Thompson
 */
package asgn2Tests;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import asgn2Train.DepartingTrain;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrainTests {

	private Integer EMPTY = 0;
	private Integer everyoneFoundASeat = 0; 
	private Integer defaultWeight = 50;
	private Integer defaultTooHeavyWeight = 500000;
	private Integer defaultSeats = 50;
	private Integer defaultPassengers = 42;
	private Integer invalidWeight = -100;
    private String defaultGoods = "G";
    @SuppressWarnings("unused")
	private String invalidGoods = "K";
    private Integer exactlyFull = 350;
    private String defaultClassification = "4S";
    private String invalidLocoClassification = "11S";
    private Integer invalidSeats = -10;
    
    
	/*
	 * Create valid locomotive 
	 * 
	 * @result no exception thrown, locomotive added to train
	 */
	@Test 
	public void testAddValidLocomotive() throws TrainException {
		
		// Create new train 
		DepartingTrain myTrain = new DepartingTrain();
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	
		myTrain.addCarriage(loco1); // add the carriage to train
		
		assertEquals(myTrain.nextCarriage(), loco1);
		
	}
	
	
	/*
	 * Create valid locomotive and freight 
	 * 
	 * @result no exception thrown, locomotive and freight added to train, returned in right order
	 */
	@Test 
	public void testAddValidLocomotiveAndFreight() throws TrainException {
		
		// Create new train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add loco carriage
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		myTrain.addCarriage(loco1);
		
		
		// Add freight carriage
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
		myTrain.addCarriage(freight1);
		
		assertEquals(myTrain.nextCarriage(), loco1);
		assertEquals(myTrain.nextCarriage(), freight1);
		
	}
	

	
	/*
	 * Create valid locomotive and passenger carriage 
	 * 
	 * @result no exception thrown, locomotive and passenger carriage added to train, returned in right order
	 */
	@Test 
	public void testAddValidLocomotiveAndPassenger() throws TrainException {
		
		// Create train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add loco
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		myTrain.addCarriage(loco1);
		
		// Add pass
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		myTrain.addCarriage(pass1);
		
		assertEquals(myTrain.nextCarriage(), loco1);
		assertEquals(myTrain.nextCarriage(), pass1);
		
	}
	
	
	
	/*
	 * Attempt to add a locomotive with invalid power
	 * 
	 * @throws TrainException
	 * @result  expected train exception thrown
	 */
	@Test (expected = TrainException.class)
	public void testAddLocoInvalidClassification() throws TrainException {
		
		// New train
		DepartingTrain myTrain = new DepartingTrain();
		
		
		// Add loco
		Locomotive loco1 = new Locomotive(defaultWeight, invalidLocoClassification);
    	myTrain.addCarriage(loco1);
		
	}
	
	
	/*
	 * Attempt to add a locomotive with an invalid weight (negative)
	 * 
	 * @throws TrainException
	 * @result expected train exception thrown
	 */
	@Test (expected = TrainException.class)
	public void testAddLocoInvalidWeight() throws TrainException {
		
		// Create train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Loco carriage
		Locomotive loco1 = new Locomotive(invalidWeight, defaultClassification);
    	myTrain.addCarriage(loco1);
		
	}
	
	
	
	/*
	 * Create a train that is exactly full. Train should not be able to move
	 * 
	 * @result trainCanMove returns true
	 */
	@Test
	public void testExactlyFull() throws TrainException {
		
		// New train
		DepartingTrain myTrain = new DepartingTrain();
		
		
		// Add carriages
		Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	myTrain.addCarriage(loco1);

		FreightCar freight = new FreightCar(exactlyFull, defaultGoods);
    	myTrain.addCarriage(freight);
    	
    	assertTrue(myTrain.trainCanMove());
		
	}
	
	
	
	/*
	 * Create a locomotive, give it more weight than it's power can handle. 
	 * 
	 * @result can move is false
	 */
	@Test 
	public void testAddLocoNeedsMorePower() throws TrainException {
		
		// New train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add loco
		Locomotive loco1 = new Locomotive(defaultTooHeavyWeight, defaultClassification);
    	myTrain.addCarriage(loco1);
		
    	assertFalse(myTrain.trainCanMove());
    	
	}
	
	
	/*
	 * Test to see if add passenger carriage throws exception when you provide 0 for weight and seats
	 * 
	 * @throws TrainException
	 * @result no carriage added, expected exception thrown
	 */
	@Test (expected = TrainException.class)
	public void testAddEmptyPassengerCarriage() throws TrainException {
		
		// Add train
		DepartingTrain myTrain = new DepartingTrain();
    	
		// Add loco
		Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		myTrain.addCarriage(loco1);
		
		// Add (invalid) passenger car
		PassengerCar pass1 = new PassengerCar(EMPTY, EMPTY);
    	myTrain.addCarriage(pass1);
		
	}
	
	
	
    /*
     * Test to see if firstCarriage() returns null when there aren't any carriages
     * 
     * @result null
     */
	@Test
	 public void testFirstCarriageReturnsNullWhenNoCarriages() throws TrainException {
		
		// Make empty train
		DepartingTrain myTrain = new DepartingTrain();
		
		assertEquals(null, myTrain.firstCarriage());
	}
	
	
	/*
	 * Test to see if addCarriage() works with valid first carriage (locomotive)
	 * 
	 * @result Carriage added, no exceptions thrown
	 */
	@Test
	public void testAddFirstCarriageValid() throws TrainException {
		
		// Train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Loco
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		myTrain.addCarriage(loco1);
		
	}	
	
	/*
	 * Test to see if addCarriage() catches error when invalid first carriage added (not locomotive) 
	 * 
	 * @throws TrainException
	 * @result expected exception - first carriage must be a locomotive
	 */
	@Test (expected = TrainException.class)
	public void testAddFirstCarriageInvalid() throws TrainException {
		
		// Train 
		DepartingTrain myTrain = new DepartingTrain();
		
		// Freight carriage (before a loco is added)
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
		myTrain.addCarriage(freight1);
		
	}
	
	/*
	 * Test to see if addCarriage() handles attempts to add two locomotive carriages 
	 * 
	 * @throws TrainException when two locomotives are attached to one train
	 * @result An expected exception
	 */	
	@Test (expected = TrainException.class)
	public void testAddCarriageTwoLocomotives() throws TrainException {
		
		// Setup train 
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add two loco motives
		Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		Locomotive loco2 = new Locomotive(defaultWeight, defaultClassification);

		myTrain.addCarriage(loco1);
		myTrain.addCarriage(loco2);
		
	}
	
	
	
	/*
	 * Test to see if addCarriage() handles attempts to add passengers AFTER Freight (invalid config)
	 * 
	 * @throws TrainException - invalid order. 1st locomotive, 2nd. passengers, 3rd. freight.
	 * @result An expected exception
	 */	
	@Test (expected = TrainException.class)
	public void testAddCarriageWrongOrder() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);

		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(pass1);
		
	}
	
	
	/*
	 * Test to see if correct carriage is returned after adding
	 * 
	 * @result carriage returned matches the one added
	 */
	@Test
	public void testFirstCarriageIsActualFirstCarriage() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add loco 
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
		myTrain.addCarriage(loco1);
		
		// Check firstCarriage returns the same as what we just added
		assertEquals(myTrain.firstCarriage(), loco1);
	}
	

	/*
	 * Check if addCarriage handles an invalid train setup correctly. First carriage must be locomotive
	 * 
	 * @throws TrainException - Invalid train configuration. First carriage must be locomotive
	 * @result An expected TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFirstCarriageNotLocomotive() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
    	PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
    	
		myTrain.addCarriage(pass1);
		
	}
	
	
	/*
	 * Test to see if code successfully handles firstCarriage() when there is
	 * more than one carriage is attached to the train
	 * 
	 * @result First carriage is returned
	 */
	@Test
	public void testFirstCarriageWithMultipleCarriages() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add multiple carriages to train
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		
		
		// First carriage still returns correct carriage
		assertEquals(loco1, myTrain.firstCarriage());
		
		
	}
	
	
	/*
	 * Test to see if nextCarriage works as expected 
	 * 
	 * @result Carriages are returned in correct order
	 */
	@Test
	public void testNextCarriageWorks() throws TrainException {
		
		// Create 3 test carriages
		DepartingTrain myTrain = new DepartingTrain();
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight1);

		// Loop through all of them and check that the returned carriage is correct
		assertEquals(loco1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(null, myTrain.nextCarriage()); // once we fall off the end, it will return null
		
	}
	
	/*
	 * Test to see if nextCarriage again after it's already been used
	 * 
	 * @result Carriages are returned in correct order
	 */
	@Test
	public void testNextCarriageWorksTwice() throws TrainException {
		
		// Create new train with carriages
		DepartingTrain myTrain = new DepartingTrain();
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight1);

		// Go through to the end of the train
		assertEquals(loco1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(null, myTrain.nextCarriage());
		
		// nextCarriage should now start from the beginning
		assertEquals(loco1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		
		
		
	}
	
	
	
	/*
	 * Test to see that the numberOfPassengers returns the correct value
	 * 
	 * @result correct number of passengers on board is returned 
	 */
	@Test
	public void testNumberOfPassengersOnboard() throws TrainException {
		
		
		// Create train with passenger carriages
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		pass1.board(defaultPassengers);
		
		PassengerCar pass2 = new PassengerCar(defaultWeight, defaultSeats);
		pass2.board(defaultPassengers);

		PassengerCar pass3 = new PassengerCar(defaultWeight, defaultSeats);
		pass3.board(defaultPassengers);

		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(pass2);
		myTrain.addCarriage(pass3);
		
		
		// Number of passengers expected
		Integer expectedPass = defaultPassengers + defaultPassengers + defaultPassengers;
		
		assertEquals(expectedPass, myTrain.numberOnBoard());
		
	}
	
	
	/*
	 * Test to see that the numberOfSeats returns the correct value
	 * 
	 * @result correct number of seats on board is returned 
	 */
	@Test
	public void testNumberOfSeatsOnboard() throws TrainException {
		
		// Create train
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		PassengerCar pass2 = new PassengerCar(defaultWeight, defaultSeats);
		
		PassengerCar pass3 = new PassengerCar(defaultWeight, defaultSeats);
		
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(pass2);
		myTrain.addCarriage(pass3);
		
		
		// total number of seats on train
		Integer expectedSeats = defaultSeats + defaultSeats + defaultSeats;
		
		assertEquals(expectedSeats, myTrain.numberOfSeats());
		
		
	}
	
	
	
	
	/*
	 * Test to see that the board() method works. This method takes the total number of passengers wanting to board and progressively adds 
	 * them into any available seats.  
	 * 
	 * @result people board the train. Board returns 0 
	 */
	@Test
	public void testBoard() throws TrainException {
		
		
		// Create a train
		DepartingTrain myTrain = new DepartingTrain();
		
		// Add carriages
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);		
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		
		// Board passengers
		assertEquals(everyoneFoundASeat, myTrain.board(defaultSeats));
		
	}
	
	
	
	/*
	 * Test to see if board() throws exception when negative number of passengers is given 
	 * 
	 * @throws TrainException - if the number of new passengers is negative
	 * @result An expected exception
	 */	
	@Test (expected = TrainException.class)
	public void testBoardNegativeException() throws TrainException {
		
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		
		myTrain.board(invalidSeats);
		
		
	}
	
	/*
	 * Test to see that the board() method works. Should return the number of people that couldn't board
	 * 
	 * @result positive integer 
	 */
	@Test
	public void testTooManyBoard() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		
		// (total of passengers) - number of the seats = number of people without a seat
		Integer numberOfPeopleSeatless = (defaultPassengers + defaultPassengers) - defaultSeats;
		
		assertEquals(numberOfPeopleSeatless, myTrain.board(defaultPassengers+defaultPassengers));
		
	}
	
	
	/*
	 * Test to see that the board() handles overflow. If there's too many people for one carriage, take them
	 * to the next carriage and the next one (if they exist) until everyone is on board.
	 * 
	 * @result positive integer 
	 */
	@Test
	public void testTooManyBoardMultipleCarriages() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		PassengerCar pass2 = new PassengerCar(defaultWeight, defaultSeats);
		PassengerCar pass3 = new PassengerCar(defaultWeight, defaultSeats);
		
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(pass2);
		myTrain.addCarriage(pass3);
		
		// (total of passengers) - ( total number of seats) = number of seats left over 
		Integer numberOfPeopleSeatless = (defaultPassengers + defaultPassengers + defaultPassengers + defaultPassengers) - (defaultSeats + defaultSeats + defaultSeats);

		assertEquals(numberOfPeopleSeatless, myTrain.board(defaultPassengers + defaultPassengers + defaultPassengers + defaultPassengers));
		
	}
	
	
	/*
	 * Test to see if the train can move the weight
	 * 
	 * @result true if the train can move. false if it cannot 
	 */
	@Test
	public void testTrainCanMove() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification); //400 power
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);		
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		
		assertTrue(myTrain.trainCanMove());
	}
	

	/*
	 * Test to see if the code recognizes that the train can't move due to the weight
	 * 
	 * @result false 
	 */
	@Test
	public void testTrainCantMove() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);		
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		
		assertFalse(myTrain.trainCanMove());
	}
	
	
	/*
	 * Test to see if the remove method works 
	 *  
	 * @result train becomes empty  
	 */
	@Test
	public void trainRemoveCarriage() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);

		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		
		myTrain.removeCarriage();
		
		assertEquals(loco, myTrain.nextCarriage());
		assertEquals(null, myTrain.nextCarriage());
		
		myTrain.addCarriage(pass1);
		myTrain.nextCarriage();
		
		assertEquals(pass1, myTrain.nextCarriage());
	}
	
	
	
	/*
	 * Test removeCarriage throws exception when there's no rollingstock on the train
	 * 
	 * @throws TrainException - no rolling stock on train
	 * @result An expected exception
	 */	
	@Test (expected = TrainException.class)
	public void testRemoveCarriageEmptyTrain() throws TrainException {
		
		
		DepartingTrain myTrain = new DepartingTrain();
		myTrain.removeCarriage();
		
		
	}
	
	
	
	
	/*
	 * Test to see if shunting operations can be performed when there are passengers on board
	 * 
	 * @throws TrainException -  no carriage shunting operations may be performed when any passengers are on board the train.
	 * @result An expected exception
	 */	
	@Test (expected = TrainException.class)
	public void testShuntWithPassengersOnBoard() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
		
		
		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight2 = new FreightCar(defaultWeight, defaultGoods);		
		
		

		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight2);
		
		myTrain.board(defaultPassengers);
		
		myTrain.removeCarriage();
		
		
		
	}
	
	
	/*
	 * Test to see if the toString train works
	 * 
	 * @result not null
	 */
	@Test
	public void trainToString() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		
		// Create a couple of carriages of every type
		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight2 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight3 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight4 = new FreightCar(defaultWeight, defaultGoods);		

		
		// Add them all to the train
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight2);
		myTrain.addCarriage(freight3);
		myTrain.addCarriage(freight4);
		
		assertNotNull(myTrain.toString());
	}
	
}
