package com.dashboard.dao;

import com.dashboard.dto.SettingsDTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Abstract superclass for a Dashboard Provider; 
 * creates the database and provides schema information to its subclasses.
 * @author Joe Rains
 *
 */
public abstract class AbstractDashboardProvider extends SQLiteOpenHelper  {
	
	// the filename for the database
	private static final String DATABASE_NAME = "DigitalDashboard.dbf";
	
	// settings table
	protected static final String SETTINGS_TBL = "settings";
	protected static final String ID_FLD = "_id";
	protected static final String BACKGROUND_MODE_FLD = "backgroundMode";
	protected static final String TRAVEL_MODE_FLD = "travelMode";
	protected static final String UNITS_FLD = "units";

	// history table
	protected static final String HISTORY_TBL = "history";
	protected static final String MAX_SPEED_FLD = "maxSpeed";
	protected static final String DISTANCE_FLD = "distance";
	
	
	/**
	 * The constructor required by SQLiteOpenHelper class.
	 * @param context the calling Activity
	 */
	protected AbstractDashboardProvider(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = null;
		
		// create the sql for the history table
		sql = new StringBuilder();
		sql.append("CREATE TABLE "+HISTORY_TBL+" (");
		sql.append(ID_FLD+" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(MAX_SPEED_FLD+" INTEGER,");
		sql.append(DISTANCE_FLD+" INTEGER);");
		
		// execute the history table sql
		db.execSQL(sql.toString());
		
		// create the sql for the settings table
		sql = new StringBuilder();
		sql.append("CREATE TABLE "+SETTINGS_TBL+" (");
		sql.append(ID_FLD+" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(BACKGROUND_MODE_FLD+" TEXT, ");
		sql.append(TRAVEL_MODE_FLD+" TEXT, ");
		sql.append(UNITS_FLD+" TEXT);");
		
		// execute the settings table sql
		db.execSQL(sql.toString());
		
		// get the default settings
		SettingsDTO settings = SettingsDTO.getDefaultSettings();		
		
		// create a ContentValues object for the settings
		ContentValues values = new ContentValues(2);
		values.put(BACKGROUND_MODE_FLD, settings.getBackgroundMode().name());
		values.put(TRAVEL_MODE_FLD, settings.getTravelMode().name());
		values.put(UNITS_FLD, settings.getUnits().name());
		
		// insert the defaults into the settings table
		db.insert(SETTINGS_TBL, UNITS_FLD, values);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// required, not used
	}
}
