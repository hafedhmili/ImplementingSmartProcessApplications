/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.Device;
import spa.samples.health.domain.DeviceModel;
import spa.samples.health.domain.DeviceState;
import spa.samples.health.domain.Location;
import spa.samples.health.domain.Patient;

/**
 * Author: Hafedh Mili
 */
public class DeviceImpl implements Device {
	
	private static int DEVICE_COUNTER = 0;
	
	private DeviceModel deviceModel;
	
	private Patient patient;
	
	private DeviceState deviceState;
	
	private String deviceID;
	
	public DeviceImpl(DeviceModel model, String...id) {
		deviceModel = model;
		if (id == null) generateID();
	}
	
	private void generateID() {
		deviceID = deviceModel.toString()+"-"+DEVICE_COUNTER;
		DEVICE_COUNTER++;
	}

	@Override
	public DeviceModel getDeviceModel() {
		return deviceModel;
	}

	@Override
	public String getDeviceID() {
		return deviceID;
	}

	@Override
	public boolean isOnPatient(Patient patient) {
		return this.patient.equals(patient);
	}

	@Override
	public DeviceState getDeviceState() {
		return deviceState;
	}

	@Override
	public void setDeviceState(DeviceState newState) {
		this.deviceState = newState;
		
	}

	@Override
	public Location getCurrentLocation() {
		// if device is on a patient, then
		// return the patient's location. 
		if (patient != null) return patient.getCurrentLocation();
		// Else, return null
		return null;
	}

	@Override
	public void attachToPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

}

