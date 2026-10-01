package spa.samples.heartpatientmonitoring.domain.types.patient;

import java.time.Instant;
import java.util.Iterator;

import spa.samples.heartpatientmonitoring.domain.types.device.DeviceType;
import spa.samples.heartpatientmonitoring.domain.types.device.MeasurementDevice;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECG;
import spa.samples.heartpatientmonitoring.domain.types.patient.medical.PatientMedicalHistory;
import spa.samples.heartpatientmonitoring.domain.types.util.Address;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;

public interface Patient {
	
	public String getSSN();
	
	public String getFirstName();
	
	public String getLastName();
	
	public Address getAddress();
	
	public void setAddress(Address address);
	
	public Location getCurrentLocation();
	
	public void setCurrentLocation(Location location);
	
	public PatientMedicalHistory getMedicalHistory();
	
	public Activity getCurrentActivity();
	
	public void setCurrentActivity(Activity activity);
	
	public Iterator<Activity> getPastActivities();
	
	public Iterator<Activity> getPastActivitiesByRecency(int...lastN);
	
	public void archiveCurrentActivity();
	
	public PatientState getCurrentState();
	
	public void setCurrentState(PatientState patientState);
	
	public Iterator<PatientState> getPastStates();
	
	public Iterator<PatientState> getPastStatesByRecency(int...lastN);
	
	public void archiveCurrentState();
	
	public ECG getMostRecentECG();
	
	public Iterator<ECG> getECGsBefore(Instant referenceTime);
	
	public Iterator<ECG> getECGsSince(Instant referenceTime);
	
	public Iterator<ECG> getECGsBetween(Instant startTime, Instant endTime);

	public Iterator<MeasurementDevice> getMeasurementDevices();
	
	public MeasurementDevice getDeviceOfType(DeviceType deviceType);
	
	public void addMeasurementDevice(MeasurementDevice device);
	

}
