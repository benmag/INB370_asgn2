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

	private Integer numOfPassengers = 0;
	private Integer numOfSeats = 0;
	
	/**
	 * @throws TrainException 
	 * 
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException {
		super(grossWeight);
	}
	
	public void alight(Integer departingPassengers) throws TrainException {
		if(departingPassengers < 0 || departingPassengers > numOfPassengers) {
			throw new TrainException("Invalid number of departing passengers.");
		} else {
			numOfPassengers -= departingPassengers;
		}
	}
	
	public Integer board(Integer newPassengers) throws TrainException {
		if(newPassengers >= 0) {
			int numOfSeatsAvailable = numOfSeats - numOfPassengers;
			int remainingPassengers = newPassengers - numOfSeatsAvailable;
			if(remainingPassengers < 0) {
			remainingPassengers = 0;
			}
			if(newPassengers > numOfSeatsAvailable) {
			numOfPassengers += numOfSeatsAvailable;
			} else {
			numOfPassengers += newPassengers;
			}
			return remainingPassengers;
		} else {
			throw new TrainException("Invalid number of passengers.");
		}
	}
	
	public Integer numberOnBoard() {
		return numOfPassengers;
	}
	
	public Integer numberOfSeats() {
		return numOfSeats;
	}

	/*
	 * Returns a human-readable description of the passenger car. This has the form "Passenger(x/y)" where x is the number of passengers currently on board and y is the number of seats in the carriage.
	 * @returns a human-readable description of the passenger car
	 */
	@Override
	public String toString() {
		return "Passenger("+numberOnBoard()+"/"+numOfSeats+")";
	}

}
