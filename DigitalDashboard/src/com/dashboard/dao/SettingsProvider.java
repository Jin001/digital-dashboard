package com.dashboard.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.dashboard.dto.BackgroundMode;
import com.dashboard.dto.SettingsDTO;
import com.dashboard.dto.TravelMode;
import com.dashboard.dto.Units;

/**
 * Data access object for the DigitalDashboard database to access settings records.
 * @author Joe Rains
 *
 */
public class SettingsProvider extends AbstractDashboardProvider implements ISettingsDAO {
	
	// sql to retrieve the most recent entry from the settings table
	private static final String SELECT_SETTINGS_SQL = 
		"SELECT "+
			BACKGROUND_MODE_FLD+", "+
			TRAVEL_MODE_FLD+", "+
			UNITS_FLD+
		" FROM "+
			SETTINGS_TBL+
		" WHERE "+
			ID_FLD+" = (SELECT MAX("+ID_FLD+") FROM "+SETTINGS_TBL+")";

	/**
	 * The constructor for a SettingsProvider requires Context to access the database.
	 * @param context the calling Activity
	 */
	public SettingsProvider(Context context) {
		super(context);
	}
	
	@Override
	public SettingsDTO getSettings() throws Exception {
		SettingsDTO settings = null;
		
		// look for the most recent settings
		Cursor c = getReadableDatabase().rawQuery(SELECT_SETTINGS_SQL, null);
		
		// get the first result
		if (c.getCount() > 0) {
			c.moveToFirst();
			
			// create a new dto for the settings
			settings = new SettingsDTO();
			settings.setBackgroundMode(BackgroundMode.valueOf(c.getString(0)));
			settings.setTravelMode(TravelMode.valueOf(c.getString(1)));
			settings.setUnits(Units.valueOf(c.getString(2)));
		}
		
		// return the stored or default settings
		return settings != null ? settings : SettingsDTO.getDefaultSettings();
	}

	@Override
	public void updateSettings(SettingsDTO settings) throws Exception {
		
		// check for null
		if (settings == null) throw new NullPointerException("No settings object was given to update");
		
		// simple validations; track any errors
		StringBuilder errorMessage = new StringBuilder();
		if (settings.getBackgroundMode() == null) errorMessage.append("No BackgroundMode was given to update");
		if (settings.getTravelMode() == null) errorMessage.append("No TravelMode was given to update");
		if (settings.getUnits() == null) errorMessage.append("No Units were given to update");
		
		// if the error message is not empty, throw an exception with its contents
		if (errorMessage.length() > 0) throw new IllegalArgumentException("Invalid settings: "+errorMessage.toString());
		
		// create the parameter values
		ContentValues values = new ContentValues(2);
		values.put(BACKGROUND_MODE_FLD, settings.getBackgroundMode().name());
		values.put(TRAVEL_MODE_FLD, settings.getTravelMode().name());
		values.put(UNITS_FLD, settings.getUnits().name());
		
		// update the record
		getWritableDatabase().update(SETTINGS_TBL, values, null, null);
	}

}
