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
    
	@Test
	 public void testFirstCarriageReturnsNullWhenNoCarriages() throws TrainException {
		DepartingTrain myTrain = new DepartingTrain();
		
		assertEquals(null, myTrain.firstCarriage());
	}
	
	
	@Test
	public void testFirstCarriageReturnsFirstCarriage() throws TrainException {
		
		DepartingTrain myTrain = new DepartingTrain();
    	FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);

    	System.out.println(freight1.goodsType());
    	
		myTrain.addCarriage(freight1);
		
	}
}
