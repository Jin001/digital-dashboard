package com.dashboard.dto;

/**
 * Enumeration for units to display in the UI:
 * <ul>
 *   <li><b>US</b>  United States Customary Units</li>
 *   <li><b>Metric</b>  Base 10 measurements used everywhere but in the US.</li>
 * </ul>
 * @author Joe Rains
 *
 */
public enum Units {

	// declare literals - US is '1' because it is the default
	US(1),
	Metric(1.61);
	
	private double conversionFactor;
	
	/**
	 * Constructor for a Units object; requires a conversion factor.
	 * @param conversionFactor
	 */
	private Units(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	/**
	 * Getter method for the conversion factor value.
	 * @return the conversion factor to use
	 */
	public double getConversionFactor() {
		return conversionFactor;
	}
	
}
