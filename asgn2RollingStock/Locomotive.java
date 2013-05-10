/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Spike
 *
 */
public class Locomotive extends RollingStock {
	private Integer pullingPower;
	private String classification;
	/**
	 * @throws TrainException 
	 * 
	 */
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		// TODO Auto-generated constructor stub
	}

}
