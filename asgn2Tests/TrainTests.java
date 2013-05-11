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

import org.junit.Test;
import static org.junit.Assert.*;

public class TrainTests {

	/*
	 * TEST LIST
	 * firstCarriage() - Returns the first train
	 * firstCarriage() - Returns null if there are no carriages
	 * firstCarriage() - Must be a locomotive
	 */
	
    private Integer defaultWeight = 50;
    private Integer invalidWeight = -100;
    private String defaultGoods = "G";
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
	 * Test to see if addCarriage() works with valid carriage 
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
	
}
