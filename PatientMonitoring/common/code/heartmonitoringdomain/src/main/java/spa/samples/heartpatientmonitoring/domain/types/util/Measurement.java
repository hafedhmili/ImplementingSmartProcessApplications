package spa.samples.heartpatientmonitoring.domain.types.util;

import java.time.Instant;

/**
 * Author: Hafedh Mili
 */
public interface Measurement {
	
	/**
	 * What type of measurement is this?
	 * @return
	 */
	public MeasurementType getMeasurementType();

	/** */
	public Instant getStartTime();
	
	/**
	 * When did the measurement end? could be the same as start time for instantaneous measurements
	 * @return
	 */
	public Instant getEndTime();

	/**
	 * Where did the measurement start? it represents the location of the patient at the time of the measurement, it could be null if the location is not known or not relevant for this measurement
	 * @return
	 */
	public Location getStartLocation();
	
	/**
	 * set the starting location of the measurement
	 * @param startLocation
	 */
	public void setStartLocation(Location startLocation);
	
	/**
	 * Where did the measurement end? it represents the location of the patient at the time of the measurement, it could be null if the location is not known or not relevant for this measurement
	 * @return
	 */
	public Location getEndLocation();
	
	public void setEndLocation(Location endLocation);
}
