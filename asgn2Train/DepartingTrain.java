/*
 * A train is a sequence of carriages. This class defines various operations that can be performed to prepare a long-distance train for departure. 
 * 
 * We assume that a train can be assembled from any available rolling stock, including locomotives, passenger cars and freight cars. However, they may be configured in only a certain sequence:
 * 
 * <ol>
 * <li>The first carriage must be a locomotive (and there can be only one locomotive per train).</li>
 * <li>This may be followed by zero or more passenger cars.</li>
 * <li>These may be followed by zero or more freight cars.</li>
 * </ol>
 * 
 * Any other configurations of rolling stock are disallowed.
 * 
 * The process of preparing the train for departure occurs in two stages:
 * <ol>
 * <li>The train is assembled from individual carriages. New carriages may be added to the rear of the train only. (Similarly, carriages may be removed from the rear of the train only.)</li>
 * <li>Passengers board the train. For safety reasons, no carriage shunting operations may be performed when any passengers are on board the train.</li>
 * </ol>
 * 
 * @version 1.0
 * @author Ben Maggacis, Corey Thompson
 * 
 */
package asgn2Train;

import java.util.ArrayList;
import java.util.List;


import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.RollingStock;
import asgn2RollingStock.PassengerCar;

public class DepartingTrain extends Object {
	
	List<RollingStock> stockList = new ArrayList<RollingStock>(); // Create a list that contains all rolling stock
	private final int EMPTY = 0, FIRST = 0, LOCOPOS = 0;
	private int currentCarriagePos = 0;
	private int precedingCarriageCall = 0; // track if firstCarriage or nextCarriage has been called
	private boolean freightCarriageAdded = false; // track if we have any passenger carriages 

	/* 
	 * Constructs a (potential) train object containing no carriages (yet).
	 */
	public DepartingTrain() {
	
		// Not really sure what to do in here yet.
		//NEW TRAIN OBJECT: DepartingTrain myTrain = new DepartingTrain();
	
	}
	
	
	/* 
	 * Returns the first carriage on the train (which must be a locomotive). Special value null is returned if there are no carriages on the train at all. 
	 * NB: When combined with method nextCarriage, this method gives us a simple ability to iteratively examine each of the train's carriages.
	 * 
	 * @returns the first carriage in the train, or null if there are no carriages
	 */
	public RollingStock firstCarriage() {
		
		if(stockList.size() > EMPTY) { // we have at least one carriage
			this.precedingCarriageCall++; // track preceding carriage call
			return stockList.get(FIRST); // get the first this MUST be a locomotive
		} else { // no carriages
			System.out.println("No carriages on this train!");
			return null;
		}
		
	}
	
	
	/*
	 * Returns the next carriage in the train after the one returned by the immediately preceding call to either this method or method firstCarriage. Special value null is returned if there is no such carriage. If there has been no preceding call to either firstCarriage or nextCarriage, this method behaves like firstCarriage, i.e., it returns the first carriage in the train, if any.
	 * NB: When combined with method firstCarriage, this method gives us a simple ability to iteratively examine each of the train's carriages.
	 * 
	 * @returns the train's next carriage after the one returned by the immediately preceding call to either firstCarriage or nextCarriage, or null if there is no such carriage
	 */
	
	public RollingStock nextCarriage() {
		
		if(currentCarriagePos < stockList.size()) {
			
			if(precedingCarriageCall == 0) {
				return firstCarriage();
			} else {
				this.currentCarriagePos++;
				return stockList.get(currentCarriagePos);	
			}
		} else {
			return null;
		}
			
	}
	
	
	/*
	 * Returns the total number of passengers currently on the train, counting all passenger cars.
	 * 
	 * @returns the number of passengers on the train
	 */
	public Integer numberOnBoard() {
  
            Integer passengerCount = 0;
            
            // Loop through, identify the passenger cars and count the number of passengers
            for (int carPos = 0; carPos < stockList.size(); carPos++) {
				if(stockList.get(carPos).toString().contains("Passenger")) { // if the car is a
					
					// Count the number of passengers
					passengerCount = passengerCount + ((PassengerCar) stockList.get(carPos)).numberOnBoard();
					
				}
			}
            
            return passengerCount;
	}
	
	
	/*
	 * Returns the total number of seats on the train (whether occupied or not), counting all passenger cars.
	 * 
	 * @returns the number of seats on the train
	 */
	public Integer numberOfSeats() {
            
        Integer seatCount = 0;
        
        // Loop through, identify the passenger cars and count the number of passengers
        for (int carPos = 0; carPos < stockList.size(); carPos++) {
			if(stockList.get(carPos).toString().contains("Passenger")) { // if the car is a
				
				// Count the number of passengers
				seatCount = seatCount + ((PassengerCar) stockList.get(carPos)).numberOfSeats();
				
			}
		}
        
        return seatCount;
		
	}
	
	/*
	 * Adds the given number of people to passenger carriages on the train. We do not specify where the passengers must sit, so they can be allocated to any vacant seat in any passenger car.
	 * 
	 * @param newPassengers - the number of people wish to board the train
	 * @returns - the number of people who were unable to board the train because they couldn't get a seat
	 * @throws - TrainException - if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		
		Integer dontFit = newPassengers;
		Integer passengersWaiting = newPassengers;
		// Loop through, find all passenger carriages 
        for (int carPos = 0; carPos < stockList.size(); carPos++) {
        	
        	// Add as many passengers into each passenger carriage 
			if(stockList.get(carPos).toString().contains("Passenger")) { // if the car is a
				
				// Board the new passengers and any other passengers that didn't fit into the carriage before.
				dontFit = ((PassengerCar) stockList.get(carPos)).board(dontFit);
				
				
				/* -- REMOVED. I'm silly. :)
				//How many can we fit in 
				Integer availableInCar = ((PassengerCar) stockList.get(carPos)).numberOfSeats() -((PassengerCar) stockList.get(carPos)).numberOnBoard();
				
				// Board the maximum amount of people into this car
				((PassengerCar) stockList.get(carPos)).board(availableInCar);
				
				// Update count of people who don't fit. Negatives means we have left over seats!
				dontFit -= availableInCar;
				*/
				
			}
		}
        
        return dontFit;	
	}
	
	/*
	 * Returns whether or not the train is capable of moving. A train can move if its locomotive's pulling power equals or exceeds the train's total weight (including the locomotive itself).
	 * 
	 * In the degenerate case of a "train" which doesn't have any rolling stock at all yet, the method returns true.
	 * 
	 * @returns true if the train can move (or contains no carriages), false otherwise
	 */
	public boolean trainCanMove() {
		
		Integer trainPower = ((Locomotive) stockList.get(LOCOPOS)).power();
		Integer trainWeight = 0;
		
		// Loop through and generate the total weight of the train
		 for (int carPos = 0; carPos < stockList.size(); carPos++) {
		      
			 trainWeight += stockList.get(carPos).getGrossWeight();
			 
		 }
		 
		 // Can it be pulled by the train?
		 if(trainPower >= trainWeight) {
			 return true;
		 } else {
			 return false;
		 }
	}
	
	
	/*
	 * Adds a new carriage to the end of the train. However, a new carriage may be added only if the resulting train configuration is valid, as per the rules listed above. Furthermore, shunting operations may not be performed if there are passengers on the train.
	 * 
	 * Hint: You may find Java's in-built instanceof operator useful when implementing this method (and others in this class).
	 * 
	 * @param - newCarriage - the new carriage to be added
	 * @throws - TrainException - if adding the new carriage would produce an invalid train configuration, or if there are passengers on the train
	 */
	public void addCarriage(RollingStock newCarriage) throws TrainException	{
		
		
		// Do we already have carriages?
		if(stockList.size() > EMPTY) {
			
			// Check if it's allowed to be added
			if(newCarriage.toString().contains("Loco") && stockList.get(FIRST).toString().contains("Loco")) {
				// We've already got one locomotive, another cannot be added.
				throw new TrainException("Invalid train configuration. Cannot addCarriage(). Only one locomotive per train. ");
			} else if(freightCarriageAdded == true && newCarriage.toString().contains("Passenger")) {
				// Can't have people carriages here. Health and Safety regulations are such a drag.
				throw new TrainException("Invalid train configuration. Cannot addCarriage() for passengers to train after freight carriages have been added."); 
			} else if(newCarriage.toString().contains("Freight") && numberOnBoard() > EMPTY) {
				throw new TrainException("Invalid train configuration. You cannot addCarriage() if people are on board");
			} else {
				
				// Track if freight carriage added to train
				if(newCarriage.toString().contains("Freight")) this.freightCarriageAdded = true;
				
				stockList.add(newCarriage);
			}
			
			// NOTE: Also need to check if people are on board. Cannot add if people are on board
			
		} else {
			
			// We only want to accept locomotive carriages for our first carriage
			if(newCarriage.toString().contains("Loco")) {
				stockList.add(newCarriage);
			} else {
				throw new TrainException("Invalid train configuration. First carriage must be a locomotive. Cannot add new carriage in addCarriage");
			}
			
		}
		
	}
	
	
	/*
	 * Removes the last carriage from the train. (This may be the locomotive if it is the only item of rolling stock on the train.) However, shunting operations may not be performed if there are passengers on the train.
	 * 
	 * @throws TrainException - if there is no rolling stock on the "train", or if there are passengers on the train.
	 */
	public void removeCarriage() throws TrainException {
		
		// FIX ME: Make me more pretty :) Need to check if there's carriages, throw exceptions etc.
		// Check if there are people on board
		if(numberOnBoard() > EMPTY) {
			throw new TrainException("Shunting operation, removeCarriage() could not be performed, there are people on board!");
		} else if(stockList.size() == EMPTY) {
			throw new TrainException("Cannot removeCarriage(). No rolling stock on the train.");
		} else {
			stockList.remove(stockList.size());	
		}
		
	}
	
	/*
	 * Returns a human-readable description of the entire train. This has the form of a hyphen-separated list of carriages, starting with the locomotive on the left. The description is thus a string "a-b-...-z", where a is the human-readable description of the first carriage (the locomotive), b is the description of the second carriage, etc, until the description of the last carriage z. (Note that there should be no hyphen after the last carriage.) For example, a possible train description may be "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages, the empty string is returned.
	 * 
	 * @override toString in class Object
	 * @returns a human-readable description of the entire train
	 */
	public String toString() { 
		
		String line1 = "";
		String line2 = "";
		String line3 = "";
		
		/*System.out.println(
				"_||__  ____ ____ ____     \n" +
				"(o)___)}___}}___}}___}   \n " +
				"'U'0 0  0 0  0 0  0 0    \n");*/
				
		// Loop through and append carriage info to train 
		 for (int carPos = 0; carPos < stockList.size(); carPos++) {
		      
			 
			 // Build carriage
			 if(stockList.get(carPos).toString().contains("Loco")) {

				 line1 = line1 + "_||_____________";
				 line2 = line2 + "(o)__"+stockList.get(carPos).toString()+"__)";
				 line3 = line3 + "'U' 0 0    0 0 ";
				
			 } else if(stockList.get(carPos).toString().contains("Passenger")) {
			
				 line1 = line1 + "  _____________________";
				 line2 = line2 + "}___"+stockList.get(carPos).toString()+"___}";
				 line3 = line3 + "      00    00    00   ";
				 
			 } else if(stockList.get(carPos).toString().contains("Freight")) {
			
				 line1 = line1 + "  ___________________";
				 line2 = line2 + "}____"+stockList.get(carPos).toString()+"_____}";
				 line3 = line3 + "     00    00    00  ";
				 
			 }
			 
			 /*   \/         \/
			  * |~~~~ TRAIN ~~~~|
			  * |     Loco(x)   |
			  * |=> 400 tonnes  |
			  * |---------------|
			  * | Passenger(x/y)|
			  * |---------------|
			  * |   Freight(x)  |
			  */
			 
		 }
		 
		 System.out.println(line1);
		 System.out.println(line2);
		 System.out.println(line3);
		 System.out.println();
		return null;
	}

}
