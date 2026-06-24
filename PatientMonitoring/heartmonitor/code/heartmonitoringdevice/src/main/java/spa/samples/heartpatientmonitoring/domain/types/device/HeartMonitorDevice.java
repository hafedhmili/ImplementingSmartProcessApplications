package spa.samples.heartpatientmonitoring.domain.types.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;

public interface HeartMonitorDevice {
	
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
	 * get the most recent recorded ECG
	 * @return
	 */
	public ECGraw getLatestECG();
	
	/**
	 * marks the <code>ecg</code> as being the latest one to be 
	 * recorded
	 * @param ecg
	 */
	public void setLatestECG(ECGraw ecg);
	
	/**
	 * returns the current location of the heart monitor. If the
	 * patient is wearing the heart monitor, then this is also the patient
	 * location
	 * @return
	 */
	public Location getCurrentLocation();
	
	/**
	 * sets the location of the ECG. This function should be called internally
	 * @param currentLocation
	 */
	public void setCurrentLocation(Location currentLocation);
	
	/**
	 * returns the start recording time for the latest ECG
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
	 * returns the end recording time for the latest ECG
	 * @return
	 */
	public Instant getLatestEndRecordingTime();
	
	/**
	 * This is called internally to set the end recording time for the latest ECG. 
	 * It is called by takeECG. 
	 * @param endRecordingTime
	 */
	public void setLatestEndRecordingTime(Instant endRecordingTime);
	/**
	 * returns the list of ECGs recorded by the heart monitor
	 * @return
	 */
	public Iterator<ECGraw> getECGs();
	
	/**
	 * Usually, latest ECGs, when completed, get added
	 * @param anECG
	 */
	public void addECG(ECGraw anECG);
	
	/**
	 * Ignoring an ECGraw for one reason or another
	 * @param anECG
	 * @return
	 */
	public ECGraw removeECG(ECGraw anECG);

	/**
	 * take an ECG with the specified format and duration. This is the 'command' to start taking an ECG. 
	 * 
	 * The heart monitor will take the ECG and add it to the list of ECGs, and mark it as the latest ECG.
	 * For the captured ECG, the start time will be set to the current time, and the end time to the current time plus the duration.
	 * The start and end location will be set to the current location of the heart monitor.
	 * 
	 * This changes the state from sleeping to recording, and then to sleeping again when the recording is done.
	 * 
	 * All of the above that the monitor is attached to a patient (patientID != null). If the patientID is null, it
	 * prints an error message and returns a null.
	 * @param duration
	 * @return
	 */
	public ECGraw takeECG(Duration duration);

	/**
	 * This method changes the state of the hearty monitor from off to sleeping.
	 */
	public void turnOn();

	/**
	 * This method changes the state of the hearty monitor from sleeping to off.
	 */
	public void turnOff();

	/**
	 * returns the current recording format for the device. 
	 * @return
	 */
	public ECGFormat getCurrentECGFormat();

	/**
	 * We assume that some device models support several recording formats, and a 
	 * given device can have the recording format set among the supported formats
	 * @param format
	 */
	public void setCurrentECGFormat(ECGFormat format) throws UnsupportedECGFormat;


	/**
	 * This method checks whether this heart monitor supports the ECGFormat <code>ecgFormat</code>.
	 * It does so by checking with the device model.
	 * @param ecgFormat
	 * @return
	 */
	public boolean supportsECGFormat(ECGFormat ecgFormat);
}
