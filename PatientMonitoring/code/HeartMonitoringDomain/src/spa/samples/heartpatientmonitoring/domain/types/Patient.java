package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

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
	
	public Iterator<PatientState> getPastSttesByRecency(int...lastN);
	
	public void archiveCurrentState();
	
	public ECG getMostRecentECG();
	
	public Iterator<ECG> getECGsBefore(Instant referenceTime);
	
	public Iterator<ECG> getECGsSince(Instant referenceTime);
	
	public Iterator<ECG> getECGsBetween(Instant startTime, Instant endTime);
	
	public HeartMonitorDevice getHeartMonitor();
	
	public void setHeartMonitorDevice(HeartMonitorDevice device);
	

}
