/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Spike
 *
 */
public abstract class RollingStock {
	int grossWeight;
	/**
	 * @param grossWeight 
	 * @throws TrainException 
	 * 
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		// TODO Auto-generated constructor stub
		if(grossWeight <= 0) {
			throw new TrainException("Invalid gross weight.");
		} else {
			this.grossWeight = grossWeight;
		}
	}
        
    public Integer getGrossWeight() {
		return grossWeight;
    }

}
