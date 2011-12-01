package com.dashboard.dao;

import com.dashboard.dto.SettingsDTO;

/**
 * Concrete implementation of an ISettingsDAO, used for the testing.
 * @author Joe Rains
 *
 */
public class SettingsProviderStub implements ISettingsDAO {

	@Override
	public SettingsDTO getSettings() throws Exception {
		
		SettingsDTO settings = null;
		try {
			// attempt to retrieve this from the database
			
			// create a default object for testing
			settings = SettingsDTO.getDefaultSettings();
			
		} catch (Exception e) {
			throw e;
		} finally {
			// close the connection
		}
		
		return settings;
	}

	@Override
	public void updateSettings(SettingsDTO settings) throws Exception {
		
		// simple validations; track any errors
		StringBuilder errorMessage = new StringBuilder();
		
		if (settings == null) {
			errorMessage.append("No settings object was given to update");
		}
		if (settings.getBackgroundMode() == null) {
			errorMessage.append("No BackgroundMode was given to update");
		}
		if (settings.getTravelMode() == null) {
			errorMessage.append("No TravelMode was given to update");
		}
		if (settings.getUnits() == null) {
			errorMessage.append("No Units were given to update");
		}
		
		// if the error message is not empty, throw an exception with its contents
		if (errorMessage.length() > 0) {
			throw new IllegalArgumentException("Invalid settings: "+errorMessage.toString());
		}
		
		// update the settings in the database
		
	}

}
