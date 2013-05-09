/*
 * Freight cars are designed to handle a variety of goods. For the purposes of 
 * this assignment we assume there are three freight car types of interest, 
 * characterised by the kinds of goods they are designed to carry:
 * 
 * * "G" - General goods
 * * "R" - Refrigerated goods
 * * "D" - Dangerous materials
 * 
 * @version 1.0
 * @author Ben Maggacis, Corey Thompson
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;


public class FreightCar extends RollingStock {
    private String goodsType;

    
    /**
    * Constructs a freight car object.
    * 
    * @param grossWeight - the freight car's gross weight (fully-laden), in tonnes
    * @param goodsType - the type of goods the car is designed to carry (either "G", "R" or "D")
    * @throws TrainException - if the gross weight is not positive or if the goods type is invalid
    */
    public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
        if(grossWeight <= 0 || !"G".equals(goodsType) && !"R".equals(goodsType) && !"D".equals(goodsType)) {
            //Invalid constructor arguments
            throw new TrainException("You did not enter a valid gross weight/goods type.");
        } else {
            //Valid constructor arguments
            this.grossWeight = grossWeight;
            this.goodsType = goodsType;
        }
    }
	
    
    /* 
     * Returns the type of goods this carriage was designed to carry. 
     * (Simulates someone checking the label on the freight car to 
     * determine what's inside.)
     * 
     * @returns the goodsType (G", "R" or "D")
     */
    public String goodsType() {
        return goodsType;
    }
    
    
    /* 
     * Returns a human-readable description of the freight car. This has the form "Freight(x)" where x is a character ("G", "R" or "D") indicating the type of goods the car is designed to carry.
     * 
     * @returns a human-readable description of the freight car
     */
    public String toString() {
        return null;      
    }

}
