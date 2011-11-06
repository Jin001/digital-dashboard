package com.dashboard.service;

import android.content.Context;

import com.dashboard.dao.HistoryProvider;
import com.dashboard.dao.SettingsProvider;
import com.dashboard.dto.HistoryDTO;
import com.dashboard.dto.SettingsDTO;

/**
 * Service which provides access to the integration and persistence logic.
 * @author Joe Rains
 *
 */
public class DashboardService implements IDashboardService {
	
	private HistoryProvider historyDAO;
	private SettingsProvider settingsDAO;
	
	/**
	 * The constructor for a DashboardService requires Context to access the database.
	 * @param context the calling Activity
	 */
	public DashboardService(Context context) {
		historyDAO = new HistoryProvider(context);
		settingsDAO = new SettingsProvider(context);
	}

	@Override
	public int getCurrentDistance() {
		// TODO replace with code by integration specialist
		int min = 9, max = 499;
		return min + (int)(Math.random()*((max - min) + 1));
	}

	@Override
	public int getCurrentSpeed() {
		// TODO replace with code by integration specialist
		int min = 9, max = 99;
		return min + (int)(Math.random()*((max - min) + 1));
	}

	@Override
	public HistoryDTO getHistory() throws Exception {
		return this.historyDAO.getHistory();
	}

	@Override
	public void updateHistory(HistoryDTO history) throws Exception {
		this.historyDAO.updateHistory(history);
	}
	
	/**
	 * Reset the values to zeroes.
	 * @throws Exception
	 */
	public void resetHistory() throws Exception {
		this.historyDAO.resetHistory();
	}

	@Override
	public SettingsDTO getSettings() throws Exception {
		return this.settingsDAO.getSettings();
	}

	@Override
	public void updateSettings(SettingsDTO settings) throws Exception {
		this.settingsDAO.updateSettings(settings);
	}

}
