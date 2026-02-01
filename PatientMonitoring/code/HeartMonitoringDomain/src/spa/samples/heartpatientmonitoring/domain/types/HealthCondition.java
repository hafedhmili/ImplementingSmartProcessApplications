package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * A HealthCondition is a "condition" of the patient with health implications, and that is worth
 * recording in the patient medical history.
 * 
 * More often than not, this is used to represent different pathologies that the patient has. A condition
 * is characterized by one or more episodes (<code>HealthConditionEpisode</code>). One-off conditions will have a
 * single episode. For example, a patient fell on an icy sidewalk, and sprained their ankle. If nothing
 * is broken, there may be a single <code>HealthEpisode</code>, which may involve an initial visity to the 
 * emergency, then some medication and physiotherapy (<clode>HealthCareEpisode</code), than a last visit 
 * to the doctor to check that everything is OK.
 * 
 * Chronic conditions, on the otyher, may have "bouts" occurring at different times at
 */
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
	 * A health condition can be suspected, diagnosed confirmed, in the process of getting managed, etc.
	 * Check the values of the enumeration HealthConditionStatus
	 * @return
	 */
	public HealthConditionStatus getStatus();
	
	/**
	 * Modify the status of the health condition
	 * @param status
	 */
	public void setStatus(HealthConditionStatus status);
	
	/**
	 * get the severity level of the health condition
	 * @return
	 */
	public SeverityLevel getSeverity();
	
	/**
	 * Modify the severity level of the heaslth condition
	 * 
	 * @param severityLevel
	 */
	public void setSeverity(SeverityLevel severityLevel);
	
	/**
	 * get the time of the last event regarding this condition. It could be a new health episode,
	 * or a visit to the doctor concerning this condition, or a diagnostic test for this condition
	 * @return
	 */
	public Instant getdateLatestUpdate();
	
	/**
	 * modify the date of latest update to the condition
	 * 
	 * @param dateLatestUpdate
	 */
	public void setDateLatestUpdate(Instant dateLatestUpdate);
	
	/**
	 * return the latest updated health episode for this condition.
	 * @return
	 */
	public HealthConditionEpisode getLatestUpdatedEpisode();
	
	/**
	 * modifies/sets the latest updated health episode to the argument.
	 * 
	 * @param healthConditionEpisode
	 */
	public void setLatestUpdateEpisode(HealthConditionEpisode healthConditionEpisode); 
	
	/**
	 * returns the health condition episode that corresponds to the onset of the condition. This could
	 * be the initial diagnosis, resulting in follow-up, or an "accident" that resulted in a chronic
	 * or long lasting condition with several episodes. For example, the person who sprained their ankle
	 * broke a ligament. This may result in a long-term condition with physiotherapy, medication, surgery, etc.
	 * @return
	 */
	public HealthConditionEpisode getOnset();
	
	public void setOnset(HealthConditionEpisode healthConditionEpisode);
	
	/**
	 * Returns the history of health episode. We cannot assume that they will be
	 * time-ordered.
	 * @return
	 */
	public Iterator<HealthConditionEpisode> getHistory();
	
	/**
	 * adds a <code>HealthConditionEpisode</code> to the list of health condition episodes 
	 * @param healthConditionEpisode
	 */
	public void addHealthConditionEpisode(HealthConditionEpisode healthConditionEpisode);
	
	public HealthConditionEpisode removeHealthConditionEpisode(HealthConditionEpisode healthConditionEpisode);
	
	/**
	 * The abatement episode corresponds to the 'conclusion' of the health condition. For example, our
	 * patiemnt with sprained ankle who broke a ligament, ended up having surgery to attach the ligament
	 * then had physiotherapy after which the condition was deemed taken care of, requesting no follow-up.
	 * @return
	 */
	public HealthConditionEpisode getAbatement();
	
	/**
	 * sets the <code>healthConditionEpisode</code> as the abatement for the condition
	 * @param healthConditionEpisode
	 */
	public void setAbatement(HealthConditionEpisode healthConditionEpisode);
}
