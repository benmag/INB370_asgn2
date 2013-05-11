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
	 * @throws TrainException 
	 * 
	 */
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		classification = classification + " ";
		if(classification.length() == 3) { //Length must equal 3 characters (two provided + a space)
			Integer firstChar = Integer.parseInt(classification.substring(0, 1));
			char secondChar = (classification.substring(1, 2).toUpperCase()).charAt(0);
			//First character must be an integer between 0 and 10 exclusive.
			if(firstChar > 0 && firstChar < 10){
				//Second character must be a string of E, D, or S.
				if(secondChar == 'E' || secondChar == 'D' || secondChar == 'S') {
					this.classification = classification;
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

	public Integer power() {
		String powerString = classification.substring(0, 1);
		Integer power = Integer.parseInt(powerString);
		return (power * 100);
	}


	/*
	 * Returns a human-readable description of the locomotive. This has the form "Loco(x)" where x is the locomotive's two-character classification code
	 * @returns a human-readable description of the locomotive
	 */
	@Override
	public String toString() {
		return "Loco("+  this.classification + ")";
	}
}
