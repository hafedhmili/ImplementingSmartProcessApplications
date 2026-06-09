package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

public interface PatientMedicalHistory {
	
	public Patient getPatient();
	
	public Instant getCreationDate();
	
	public Instant getDateLatestUpdate();
	
	public void setDateLatestUpdate(Instant date);
	
	public HealthCondition getLatestUpdatedHealthCondition();
	
	public void setLatestUpdatedHealthCondition(HealthCondition healthCondition);
	
	public void addHealthCondition(HealthCondition healthCondition);
	
	public HealthCondition removeHealthCondition(HealthCondition healthCondition);

	public Iterator<HealthCondition> getHealthConditions();

}
