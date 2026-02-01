package spa.samples.heartpatientmonitoring.domain.types;

public interface HealthCondition {
	
	/**
	 * returns the patient medical history of which this is a condition
	 * @return
	 */
	public PatientMedicalHistory getPatientMedicalHistory();

	/**
	 * returns the classification of the health condition. We take the most specific
	 * applicable type
	 * @return
	 */
	public HealthConditionType getHealthConditionType();
	
	/**
	 * A healthj condition can be suspected, diagnosed confirmed, in the process of getting managed, etc.
	 * Check the values of the enumeration HealthConditionStatus
	 * @return
	 */
	public HealthConditionStatus getStatus();
	
	public void setStatus(HealthConditionStatus status);
	
	public SeverityLevel getSeverity();
	
	public void setSeverity(SeverityLevel severityLevel);
}
