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

	/*
	 * TEST LIST
	 * firstCarriage() - Returns the first train
	 * firstCarriage() - Returns null if there are no carriages
	 * firstCarriage() - Must be a locomotive
	 */
	private Integer everyoneFoundASeat = 0; 
	private Integer defaultWeight = 50;
	private Integer defaultTooHeavyWeight = 500000;
	private Integer defaultSeats = 50;
	private Integer defaultPassengers = 42;
    @SuppressWarnings("unused")
	private Integer invalidWeight = -100;
    private String defaultGoods = "G";
    @SuppressWarnings("unused")
	private String invalidGoods = "K";
    private String defaultClassification = "4S";
    
    /*
     * Test to see if firstCarriage() returns null when there aren't any carriages
     * 
     * @result null
     */
	@Test
	 public void testFirstCarriageReturnsNullWhenNoCarriages() throws TrainException {
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
		
		DepartingTrain myTrain = new DepartingTrain();
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
		
		DepartingTrain myTrain = new DepartingTrain();
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
		
		DepartingTrain myTrain = new DepartingTrain();
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
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	
		myTrain.addCarriage(loco1);
		
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
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(freight1);
		
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
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		
		assertEquals(loco1, myTrain.firstCarriage());
		
		
	}
	
	
	/*
	 * Test to see if nextCarriage works as expected 
	 * 
	 * @result Carriages are returned in correct order
	 */
	@Test
	public void testNextCarriageWorks() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight1);

		assertEquals(loco1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(null, myTrain.nextCarriage());
		
	}
	
	/*
	 * Test to see if nextCarriage again after it's already been used
	 * 
	 * @result Carriages are returned in correct order
	 */
	@Test
	public void testNextCarriageWorksTwice() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
    	Locomotive loco1 = new Locomotive(defaultWeight, defaultClassification);
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
    	
		myTrain.addCarriage(loco1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight1);

		assertEquals(loco1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(freight1, myTrain.nextCarriage());
		assertEquals(null, myTrain.nextCarriage());
		
		// nextCarriage should now start from the beginning
		assertEquals(loco1.toString(), myTrain.nextCarriage().toString());
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
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		PassengerCar pass2 = new PassengerCar(defaultWeight, defaultSeats);
		
		PassengerCar pass3 = new PassengerCar(defaultWeight, defaultSeats);
		
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(pass2);
		myTrain.addCarriage(pass3);
		
		Integer expectedSeats = defaultSeats + defaultSeats + defaultSeats;
		
		assertEquals(expectedSeats, myTrain.numberOfSeats());
		
		
	}
	
	
	
	
	/*
	 * Test to see that the board() method works. This method takes the total number of passengers wanting to board and progressively adds 
	 * them into any available seats.  
	 * 
	 * @result people board the train
	 */
	@Test
	public void testBoard() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultWeight, defaultSeats);
		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		
		assertEquals(everyoneFoundASeat, myTrain.board(defaultSeats));
		
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
	 * Test to see if the code recongizes that the train can't move due to the weight
	 * 
	 * @result false 
	 */
	@Test
	public void trainToString() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);
				
		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight2 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight3 = new FreightCar(defaultWeight, defaultGoods);		
		FreightCar freight4 = new FreightCar(defaultWeight, defaultGoods);		

		
		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight2);
		myTrain.addCarriage(freight3);
		myTrain.addCarriage(freight4);
		
		myTrain.toString();
	}
	
}
