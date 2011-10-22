package com.dashboard.dao;

import com.dashboard.dto.HistoryDTO;

/**
 * Concrete instantiation of a HistoryProvider, used for the testing.
 * @author Joe Rains
 *
 */
public class HistoryProviderStub implements IHistoryDAO {

	@Override
	public HistoryDTO getHistory() throws Exception {
		
		HistoryDTO history = null;
		try {
			// TODO attempt to retrieve this from the database
			
			// create a dummy object for testing
			history = new HistoryDTO();
			history.setMaxSpeed(95); // miles per hour
			history.setDistance(545); // miles
			
		} catch (Exception e) {
			throw e;
		} finally {
			// TODO close the connection
		}
		
		return history;
	}

	@Override
	public void updateHistory(HistoryDTO history) throws Exception {
		
		if (history == null) {
			throw new NullPointerException("No history object was given to update");
		}
		
		// simple validations; track any errors
		StringBuilder errorMessage = new StringBuilder();
		if (history.getMaxSpeed() < 0) {
			errorMessage.append("The max speed cannot be a negative number ["+history.getMaxSpeed()+"]");
		}
		if (history.getDistance() < 0) {
			if (errorMessage.length() > 0) {
				errorMessage.append("; ");
			}
			errorMessage.append("The distance traveled cannot be a negative number ["+history.getDistance()+"]");
		}
		
		// if the error message is not empty, throw an exception with its contents
		if (errorMessage.length() > 0) {
			throw new IllegalArgumentException("Invalid history: "+errorMessage.toString());
		}
		
		// get the most recent history from the database
		HistoryDTO currentHistory = getHistory();
		
		// set the values
		currentHistory.setDistance(currentHistory.getDistance() + history.getDistance());
		if (history.getMaxSpeed() > currentHistory.getMaxSpeed()) {
			currentHistory.setMaxSpeed(history.getMaxSpeed());
		}
		
		// update the record
		try {
			// TODO update the record in the database
		} catch (Exception e) {
			throw e;
		} finally {
			// TODO close the connection
		}
		
	}

}
