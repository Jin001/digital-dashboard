package com.dashboard.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dashboard.dto.BackgroundMode;
import com.dashboard.dto.SettingsDTO;
import com.dashboard.dto.TravelMode;
import com.dashboard.dto.Units;

/**
 * Data access object for the DigitalDashboard database to access settings records.
 * @author Joe Rains
 *
 */
public class SettingsProvider extends SQLiteOpenHelper implements ISettingsDAO {

	/**
	 * The constructor for a SettingsProvider requires Context to access the database.
	 * @param context the calling Activity
	 */
	public SettingsProvider(Context context) {
		super(context, "DigitalDashboard", null, 1);
	}

	@Override
	public SettingsDTO getSettings() throws Exception {
		
		// look for the most recent settings
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT backgroundMode, travelMode, units ");
		sql.append("FROM settings WHERE _id = (SELECT MAX(_id) FROM settings)");
		Cursor c = getReadableDatabase().rawQuery(sql.toString(), null);
		
		// get the first result
		SettingsDTO settings = null;
		if (c.getCount() > 0) {
			c.moveToFirst();
			
			settings = new SettingsDTO();
			settings.setBackgroundMode(BackgroundMode.valueOf(c.getString(0)));
			settings.setTravelMode(TravelMode.valueOf(c.getString(1)));
			settings.setUnits(Units.valueOf(c.getString(2)));
			
		} else {
			settings = SettingsDTO.getDefaultSettings();
			
			// create the parameter values
			ContentValues values = new ContentValues(2);
			values.put("backgroundMode", settings.getBackgroundMode().name());
			values.put("travelMode", settings.getTravelMode().name());
			values.put("units", settings.getUnits().name());
			
			// insert the record
			getWritableDatabase().insert("settings", "units", values);
		}
		
		// return the stored or default settings
		return settings;
	}

	@Override
	public void updateSettings(SettingsDTO settings) throws Exception {
		
		// create the parameter values
		ContentValues values = new ContentValues(2);
		values.put("backgroundMode", settings.getBackgroundMode().name());
		values.put("travelMode", settings.getTravelMode().name());
		values.put("units", settings.getUnits().name());
		
		// update the record
		getWritableDatabase().update("settings", values, null, null);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the schema
		StringBuilder sql = new StringBuilder();
		
		// create the sql for the settings table
		sql = new StringBuilder();
		sql.append("CREATE TABLE settings (");
		sql.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append("backgroundMode TEXT, ");
		sql.append("travelMode TEXT, ");
		sql.append("units TEXT);");
		
		// execute the setting table sql
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing
	}

}
