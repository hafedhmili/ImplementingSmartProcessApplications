/**
 * 
 */
package spa.samples.health.domain;

import java.time.Instant;

/**
 * 
 * Represents timed events. It is a comparable based on time of occurrence
 * Author: Hafedh Mili
 */
public interface TimedEvent extends Comparable {
	
	public Instant getTimeOfOccurrence();

}
