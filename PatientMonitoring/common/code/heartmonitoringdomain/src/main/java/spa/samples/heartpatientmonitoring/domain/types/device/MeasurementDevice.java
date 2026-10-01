package spa.samples.heartpatientmonitoring.domain.types.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;
import spa.samples.heartpatientmonitoring.domain.types.util.MeasurementType;

public interface MeasurementDevice {
    	/**
	 * return the device ID
	 * @return
	 */
	public String getDeviceID();
	
	/**
	 * return the patient to whom the heart monitor
	 * ios attached
	 * @return
	 */
	public String getPatientID();
	
	/**
	 * (sets/assigns the monitor to) patient with <code>patientID</code>
	 * @param patientID
	 */
	public void setPatientID(String patientID);
	
	/**
	 * return the model of the device
	 * @return
	 */
	public DeviceModel getDeviceModel();
	
	/**
	 * get the device state. Could be off, reading, recording,
	 * transmitting, etc.
	 * @return
	 */
	public DeviceState getDeviceState();
	
	/**
	 * returns the recording modality of the heart monitor. For the time being,
	 * it is a combination of duration  (e.g. 90 seconds) and frequency (e.g. every hour).
	 * @return
	 */
	public RecordingModality getRecordingModality();
	
	/**
	 * sets the recording modality. This may be done by the decisions 
	 * <code>RPMA.needsToCheck(latestECG, oldTime,latestTime)</code>, 
	 * <code>RPMA.handleDifference(oldECG,oldTime,latestECG,latestTime)</code>, and 
	 * <code>RPMA.handleProblematicECG(latestECG,latestTime, ECGHistory)</code>.
	 * 
	 * @param recordingModality
	 */
	public void setRecordingModality(RecordingModality recordingModality);
	
	/**
	 * change the device state to <code>deviceState</code>
	 * @param deviceState
	 */
	public void setDeviceState(DeviceState deviceState);
	
	/**
	 * get the most recent recorded measurement
	 * @return
	 */
	public Measurement getLatestMeasurement();
	
    /**
     * returns the type of the measurement taken by this device. For example, ECG, Blood Pressure, etc.
     * The information is stored in the device model.
     * 
     * @return
     */
    public MeasurementType getMeasurementType();
	/**
	 * marks the <code>measurement</code> as being the latest one to be 
	 * recorded
	 * @param measurement
	 */
	public void setLatestMeasurement(Measurement measurement);
	
	/**
	 * returns the current location of the measurement device. If the
	 * patient is wearing the measurement device, then this is also the patient
	 * location
	 * @return
	 */
	public Location getCurrentLocation();
	
	/**
	 * sets the location of the measurement. This function should be called internally
	 * @param currentLocation
	 */
	public void setCurrentLocation(Location currentLocation);
	
	/**
	 * returns the start recording time for the latest measurement
	 * @return
	 */
	public Instant getLatestStartRecordingTime();
	
	/**
	 * This is called internally to set the start recording time for the latest ECG. 
	 * It is called by takeECG. 
	 * @param startRecordingTime
	 */
	public void setLatestStartRecordingTime(Instant startRecordingTime);
	
	/**
	 * returns the end recording time for the latest measurement
	 * @return
	 */
	public Instant getLatestEndRecordingTime();
	
	/**
	 * This is called internally to set the end recording time for the latest measurement. 
	 * It is called by takeECG. 
	 * @param endRecordingTime
	 */
	public void setLatestEndRecordingTime(Instant endRecordingTime);
	/**
	 * returns the list of measurements recorded by the measurement device
	 * @return
	 */
	public Iterator<Measurement> getMeasurements();
	
	/**
	 * Usually, latest measurement, when completed, gets added
	 * @param anECG
	 */
	public void addMeasurement(Measurement aMeasurement);
	
	/**
	 * Ignoring a measurement for one reason or another
	 * @param aMeasurement
	 * @return
	 */
	public Measurement removeMeasurement(Measurement aMeasurement);

	/**
	 * take an measurement with the specified format and duration. This is the 'command' to start taking an ECG. 
	 * 
	 * The measurement device will take the measurement and add it to the list of measurements, and mark it as the latest measurement.
	 * For the captured measurement, the start time will be set to the current time, and the end time to the current time plus the duration.
	 * The start and end location will be set to the current location of the heart monitor.
	 * 
	 * This changes the state from sleeping to recording, and then to sleeping again when the recording is done.
	 * 
	 * All of the above that the monitor is attached to a patient (patientID != null). If the patientID is null, it
	 * prints an error message and returns a null.
	 * @param duration
	 * @return
	 */
	public Measurement takeMeasurement(Duration duration);

	/**
	 * This method changes the state of the heart monitor from off to sleeping.
	 */
	public void turnOn();

	/**
	 * This method changes the state of the heart monitor from sleeping to off.
	 */
	public void turnOff();

}
