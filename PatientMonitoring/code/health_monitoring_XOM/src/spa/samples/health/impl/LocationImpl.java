/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.Location;

/**
 * Author: Hafedh Mili
 */
public class LocationImpl implements Location {
	
	private double latitude;
	
	private double longitude;
	
	public LocationImpl(double lat, double longit) {
		this.latitude = lat;
		this.longitude = longit;
	}

	@Override
	public double getLatitude() {
		return latitude;
	}

	@Override
	public double getLogitude() {
		return longitude;
	}

}
