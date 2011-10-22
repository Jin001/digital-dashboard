package com.dashboard.dto;

/**
 * Enumeration for the mode of travel being used:
 * <ul>
 *   <li><b>Walk</b>  The user is walking (least velocity); less frequent pinging required</li>
 *   <li><b>Bike</b>  The user is biking (moderate velocity); moderately frequent pinging required</li>
 *   <li><b>MotorVehicle</b>  The user is in a car or other fast moving vehicle; most frequent pinging required</li>
 * </ul>
 * @author Joe Rains
 *
 */
public enum TravelMode {

	// declare literals
	Walk("Walking"),
	Bike("Biking"),
	MotorVehicle("Driving");
	
	private String displayName;
	
	
	/**
	 * Constructor for a TravelMode object; requires a display name.
	 * @param displayName the value to use if displayed in the UI
	 */
	private TravelMode(String displayName) {
		this.displayName = displayName;
	}
	
	
	/**
	 * Getter method for the display name value.
	 * @return the UI display name
	 */
	public String getDisplayName() {
		return displayName;
	}
}
