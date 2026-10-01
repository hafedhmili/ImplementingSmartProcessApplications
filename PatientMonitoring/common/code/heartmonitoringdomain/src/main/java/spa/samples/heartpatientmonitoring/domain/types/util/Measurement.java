package spa.samples.heartpatientmonitoring.domain.types.util;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.device.MeasurementDevice;

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
	
	/**
	 * 
	 * @param endLocation
	 */
	public void setEndLocation(Location endLocation);

	/**
	 * returns the measurement device that took this measurement. 
	 * It could be null if the measurement was not taken by a device, for example, a manual measurement
	 * @return
	 */
	public MeasurementDevice getMeasurementDevice();

	/**
	 * get the ID of the measurement device that took this measurement. The
	 * difference between this method and the getMeasurementDevice() method is
	 * that this method does not require to have a live (in memory) representation
	 * of the device, which may not be true at the (IoT) client end.
	 * 
	 * @return
	 */
	public String getMeasurementDeviceId();
}
