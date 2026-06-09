package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;

public interface ClinicalTestResult {

	public Patient getPatient();
	
	public Instant getStartTime();
	
	public Instant getEndTime();

	public Location getStartLocation();
	
	public void setStartLocation(Location startLocation);
	
	public Location getEndLocation();
	
	public void setEndLocation(Location endLocation);

}
