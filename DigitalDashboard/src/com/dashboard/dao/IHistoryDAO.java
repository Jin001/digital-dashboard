package com.dashboard.dao;

import com.dashboard.dto.HistoryDTO;

/**
 * Interface definitions for a HistoryDAO object.  Provides data access
 * for a user's history in the database.
 * @author Joe Rains
 *
 */
public interface IHistoryDAO {

	/**
	 * Returns the user's history from the database.<br/>
	 * Returns null if this is the first time the application is run.
	 * @return a {@link HistoryDTO} object with the users history
	 * @throws Exception 
	 */
	public HistoryDTO getHistory() throws Exception;
	
	
	/**
	 * Updates the user's history in the database.
	 * @param history the new history to update
	 * @throws Exception
	 */
	public void updateHistory(HistoryDTO history) throws Exception;

	
	/**
	 * Resets the history values to zeroes.
	 * @throws Exception 
	 */
	public void resetHistory() throws Exception;
}
