package spa.samples.health.domain;

public interface Device {
	
	/**
	 * The corresponding device's model. All the information
	 * about what measurements it supports, precision, and the 
	 * like, is available in/through the <code>DeviceModel</code>
	 * description
	 * @return
	 */
	public DeviceModel getDeviceModel();
	
	/**
	 * The device ID
	 * @return
	 */
	public String getDeviceID();
	
	/**
	 * checks whether the device is currently on <code>patient</code>
	 * @param patient
	 * @return
	 */
	public boolean isOnPatient(Patient patient);
	
	/**
	 * returns the current state of the device
	 * @return
	 */
	public DeviceState getDeviceState();
	
	/**
	 * sets the state of the device to <code>newState</code>
	 * @param newState
	 */
	public void setDeviceState(DeviceState newState);

	/**
	 * Returns the current location of the device
	 * @return
	 */
	public Location getCurrentLocation();
	
	/**
	 * attach device to patient
	 * @param patient
	 */
	public void attachToPatient(Patient patient);
	
	/**
	 * return the <code>patient</code> to whom the device is
	 * attached
	 * @return
	 */
	public Patient getPatient();
	 
}
