package spa.samples.health.domain;

/**
 * Represents a physical location in terms of GPS
 * coordinates. It is used to locate patients, and
 * may be used to decided what to do about certain
 * conditions
 * @author hafedhmili
 *
 */
public interface Location {
	
	/**
	 * Returns the latitude
	 * @return
	 */
	public double getLatitude();
	
	/**
	 * Returns the longitude
	 * @return
	 */
	
	public double getLogitude();

}
