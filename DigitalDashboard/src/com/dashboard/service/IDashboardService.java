package com.dashboard.service;

import com.dashboard.dto.HistoryDTO;
import com.dashboard.dto.SettingsDTO;

/**
 * Inteface definitions for a DashboardService object.
 * @author Joe Rains
 *
 */
public interface IDashboardService {
	
	/***
	 * Calculates the current average velocity using GPS and time elapsed.<br/>
	 * If the units are set to metric, convert 
	 * @return the user's current average velocity
	 */
	public int getCurrentSpeed();
		
	/**
	 * Gets the current distance traveled.
	 * @return the current distance traveled (tripometer)
	 */
	public int getCurrentDistance();
		
	/**
	 * Gets the user's history; displays max speed, total distance traveled.
	 * @return a {@link HistoryDTO} object with the user's history
	 * @throws Exception 
	 */
	public HistoryDTO getHistory() throws Exception;
	
	
	/**
	 * Gets the user's currently selected preferences.
	 * @return a {@link SettingsDTO} object with the user's settings
	 * @throws Exception 
	 */
	public SettingsDTO getSettings() throws Exception;
	
	/**
	 * Updates the user's history, i.e. the max speed and total distance traveled.
	 * @param history a {@link HistoryDTO} object with the values to use
	 * @throws Exception 
	 */
	public void updateHistory(HistoryDTO history) throws Exception;
		
	/**
	 * Updates the user's settings, i.e background mode, units, and travel mode.
	 * @param settings a {@link SettingsDTO} object with the values to use
	 * @throws Exception 
	 */
	public void updateSettings(SettingsDTO settings) throws Exception;
	
	/**
	 * Resets the history values to zeroes.
	 * @throws Exception 
	 */
	public void resetHistory() throws Exception;
}
