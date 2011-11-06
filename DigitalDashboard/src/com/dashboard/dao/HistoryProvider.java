package com.dashboard.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dashboard.dto.HistoryDTO;

/**
 * Data access object for the DigitalDashboard database to access history records.
 * @author Joe Rains
 *
 */
public class HistoryProvider extends SQLiteOpenHelper implements IHistoryDAO {

	/**
	 * The constructor for a HistoryProvider requires Context to access the database.
	 * @param context the calling Activity
	 */
	public HistoryProvider(Context context) {
		super(context, "DigitalDashboard", null, 1);
	}

	@Override
	public HistoryDTO getHistory() throws Exception {

		HistoryDTO history = new HistoryDTO();

		// look for the most recent history
		String sql = "SELECT maxSpeed, Distance FROM history WHERE _id = (SELECT MAX(_id) FROM history)";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		// get the first result
		if (c.getCount() > 0) {
			c.moveToFirst();
			history.setMaxSpeed(c.getInt(0));
			history.setDistance(c.getInt(1));
		}
		
		return history;
	}

	@Override
	public void updateHistory(HistoryDTO history) throws Exception {
		
		// check for null
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
		history.setDistance(history.getDistance() + currentHistory.getDistance());
		if (history.getMaxSpeed() < currentHistory.getMaxSpeed()) {
			history.setMaxSpeed(currentHistory.getMaxSpeed());
		}
		
		// create the parameter values
		ContentValues values = new ContentValues(2);
		values.put("maxSpeed", history.getMaxSpeed());
		values.put("distance", history.getDistance());
		
		// insert the record
		getWritableDatabase().insert("history", "distance", values);
	}
	
	/**
	 * Resets the history values to zeroes.
	 */
	public void resetHistory() {
		
		// create the parameter values
		ContentValues values = new ContentValues(2);
		values.put("maxSpeed", 0);
		values.put("distance", 0);
		
		// insert the record
		getWritableDatabase().insert("history", "distance", values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the schema
		StringBuilder sql = new StringBuilder();
		
		// create the sql for the history table
		sql.append("CREATE TABLE history (");
		sql.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append("maxSpeed INTEGER,");
		sql.append("distance INTEGER);");
		
		// execute the history table sql
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing
	}

}
