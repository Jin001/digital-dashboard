package com.dashboard.dto;

/**
 * Data transfer object for the current selected preferences.
 * @author Joe Rains
 *
 */
public class SettingsDTO {
	
	private static final BackgroundMode DEFAULT_BACKGROUND_MODE = BackgroundMode.Day;
	private static final TravelMode DEFAULT_TRAVEL_MODE = TravelMode.MotorVehicle;
	private static final Units DEFAULT_UNITS = Units.US;

	private BackgroundMode backgroundMode;
	private TravelMode travelMode;
	private Units units;
	
	
	/**
	 * The default settings to use.
	 * @return the default settings to use
	 */
	public static SettingsDTO getDefaultSettings() {
		SettingsDTO settings = new SettingsDTO();
		settings.setBackgroundMode(DEFAULT_BACKGROUND_MODE);
		settings.setTravelMode(DEFAULT_TRAVEL_MODE);
		settings.setUnits(DEFAULT_UNITS);
		return settings;
	}
	
	/* Getters and Setters */
	public BackgroundMode getBackgroundMode() {
		return backgroundMode;
	}
	public void setBackgroundMode(BackgroundMode backgroundMode) {
		this.backgroundMode = backgroundMode;
	}
	public TravelMode getTravelMode() {
		return travelMode;
	}
	public void setTravelMode(TravelMode travelMode) {
		this.travelMode = travelMode;
	}
	public Units getUnits() {
		return units;
	}
	public void setUnits(Units units) {
		this.units = units;
	}
	
}
