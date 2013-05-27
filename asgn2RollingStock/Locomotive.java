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
	private String classification;
	/**
	 * The default constructor for the locomotive. Passes the weight to the RollingStock
	 * constructor, and validates the classification (power/engine type) before passing
	 * that to the local variable. Throws a train exception for invalid train, engine, or both types.
	 * @throws TrainException 
	 * 
	 */
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		String classification_temp = classification + " ";
		if(classification_temp.length() == 3) { //Length must equal 3 characters (two provided + a space)
			Integer firstChar = Integer.parseInt(classification_temp.substring(0, 1));
			char secondChar = (classification_temp.substring(1, 2).toUpperCase()).charAt(0);
			//First character must be an integer between 0 and 10 exclusive.
			if(firstChar > 0 && firstChar < 10){
				//Second character must be a string of E, D, or S.
				if(secondChar == 'E' || secondChar == 'D' || secondChar == 'S') {
					this.classification = classification; //Set as the original 2 character classification
				} else {
					throw new TrainException("Invalid engine type.");
				}
			} else {
				throw new TrainException("Invalid power class.");
			}
		} else {
			throw new TrainException("Invalid Engine and Power types.");
		}
	}
	
	/**
	 * Returns the pulling power of the locomotive.
	 * Pulling power = power classification * 100;
	 * Pulling power is in tonnes (e.g. 4 * 100 = 400 tonnes can be pulled.
	 * 
	 * @return The pulling power (100 - 900)
	 */
	public Integer power() {
		String powerString = classification.substring(0, 1);
		Integer power = Integer.parseInt(powerString);
		return (power * 100);
	}


	/**
	 * Returns a human-readable description of the locomotive. This has the form "Loco(x)" 
	 * where x is the locomotive's two-character classification code
	 * @returns a human-readable description of the locomotive
	 */
	@Override
	public String toString() {
		return "Loco("+this.classification+")";
	}
}
