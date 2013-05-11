/*
 * A simple class for exceptions thrown by railway shunting and boarding operations.
 * 
 * @version 1.0
 * @author Ben Maggacis, Corey Thompson
 */

package asgn2Exceptions;
public class TrainException extends Exception {

    
    /* 
     * Constructs a new TrainException object.
     * 
     * @param message - an informative message describing the cause of the problem
     */
    public TrainException(String message) {
    	super("Train Exception: " + message);
    	System.out.println("Train Exception: "+message); // REMOVE ME. ONLY HERE FOR TESTING PURPOSES
    }
}
