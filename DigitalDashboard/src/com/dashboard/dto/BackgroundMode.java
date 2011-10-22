package com.dashboard.dto;

/**
 * Enumeration for background modes:
 * <ul>	  
 *   <li><b>Day mode:</b>  easier to see in sunlight</li>
 * 	 <li><b>Night mode:</b>  doesn't blind you when traveling at  night</li>
 * </ul>
 * @author Joe Rains
 *
 */
public enum BackgroundMode {
	
	// declare literals
	Day("Day Mode"),
	Night("Night Mode");
	
	private String displayName;
	
	/**
	 * Constructor for a BackgroundMode object; requires a display name.
	 * @param displayName the value to use if displayed in the UI
	 */
	private BackgroundMode(String displayName) {
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
