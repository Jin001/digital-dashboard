package com.dashboard.dao;

import com.dashboard.dto.SettingsDTO;

/**
 * Interface definitions for a SettingsDAO object.  Provides data access
 * for a user's settings in the database.
 * @author Joe Rains
 *
 */
public interface ISettingsDAO {

	/**
	 * Returns the user's settings from the database.<br/>
	 * Returns null if this is the first time the application is run.
	 * @return a {@link SettingsDTO} object with the users settings
	 * @throws Exception
	 */
	public SettingsDTO getSettings() throws Exception;
	
	
	/**
	 * Updates the user's settings in the database.
	 * @param settings the new settings to update
	 * @throws Exception
	 */
	public void updateSettings(SettingsDTO settings) throws Exception;
	
}