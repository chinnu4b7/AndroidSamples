package com.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Points implements Serializable{
	double latitude;
	double longitude;
	long id;
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
}
