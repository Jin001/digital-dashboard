package com.dashboard.service;

import com.dashboard.dao.HistoryProviderStub;
import com.dashboard.dao.IHistoryDAO;
import com.dashboard.dao.ISettingsDAO;
import com.dashboard.dao.SettingsProviderStub;
import com.dashboard.dto.HistoryDTO;
import com.dashboard.dto.SettingsDTO;

/**
 * Concrete instantiation of a DashboardService, used for the testing.
 * @author Joe Rains
 *
 */
public class DashboardServiceStub implements IDashboardService {
	
	private IHistoryDAO historyDAO;
	private ISettingsDAO settingsDAO;
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
		// testing value
		return distance; // miles
	}

	@Override
	public int getCurrentSpeed() {
		// testing value
		int min = 9, max = 99;
		return min + (int)(Math.random()*((max - min) + 1)); // miles per hour
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

}
