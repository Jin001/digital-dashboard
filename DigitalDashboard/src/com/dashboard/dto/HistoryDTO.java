package com.dashboard.dto;

/**
 * Data transfer object for the user's applicaton history.
 * @author Joe Rains
 *
 */
public class HistoryDTO {

	private int maxSpeed;
	private int distance;
	
	/* Getters and Setters */
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
