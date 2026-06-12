/**
 * 
 */
package spa.samples.heartpatientmonitoring.domain.types.util;

import java.time.Instant;

/**
 * Author: Hafedh Mili
 */
public interface Measurement {
	
	public Instant getStartTime();
	
	public Instant getEndTime();

	public Location getStartLocation();
	
	public void setStartLocation(Location startLocation);
	
	public Location getEndLocation();
	
	public void setEndLocation(Location endLocation);
}
