/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Corey Thompson
 *
 */
public class PassengerCar extends RollingStock{

	/**
	 * @throws TrainException 
	 * 
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException {
		super(grossWeight);
	}

}
