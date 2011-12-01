package com.dashboard.service;

import com.dashboard.dao.HistoryProviderStub;
import com.dashboard.dao.IHistoryDAO;
import com.dashboard.dao.ISettingsDAO;
import com.dashboard.dao.SettingsProviderStub;
import com.dashboard.dto.HistoryDTO;
import com.dashboard.dto.SettingsDTO;

/**
 * Concrete implementation of IDashboardService, used for the testing.
 * @author Joe Rains
 *
 */
public class DashboardServiceStub implements IDashboardService {
	
	private IHistoryDAO historyDAO;
	private ISettingsDAO settingsDAO;
	
	// TODO Replace this with the integration stub class
	private int distance = 12500;
	
	
	/**
	 * No-argument constructor for a DashboardServiceStub.<br/>
	 * Uses stub implementations of the DAO objects for testing. 
	 */
	public DashboardServiceStub() {
		this.historyDAO = new HistoryProviderStub();
		this.settingsDAO = new SettingsProviderStub();
	}
	
	@Override
	public int getCurrentDistance() {
		// return the testing value
		// TODO Replace this with the integration stub class
		return distance;
	}

	@Override
	public int getCurrentSpeed() {
		int speed = -1;
		
		// get a testing value (miles per hour)
		// TODO Replace this with the integration stub class
		int min = 9, max = 99;
		speed = min + (int)(Math.random()*((max - min) + 1));
		
		return speed;
	}

	@Override
	public HistoryDTO getHistory() throws Exception {
		// get the user history from the dao
		return getHistoryDAO().getHistory();
	}

	@Override
	public SettingsDTO getSettings() throws Exception {
		// get the current user settings
		return getSettingsDAO().getSettings();
	}

	@Override
	public void updateHistory(HistoryDTO history) throws Exception {
		// update the user history using the dao
		getHistoryDAO().updateHistory(history);
	}
	
	@Override
	public void updateSettings(SettingsDTO settings) throws Exception {
		// update the user settings using the dao
		getSettingsDAO().updateSettings(settings);
	}

	/* Getter for the DAOs */
	public IHistoryDAO getHistoryDAO() {
		return historyDAO;
	}
	public ISettingsDAO getSettingsDAO() {
		return settingsDAO;
	}

	@Override
	public void resetHistory() {
		// required, not used
	}

}
