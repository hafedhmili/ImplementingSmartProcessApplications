package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

public interface HealthCareAct {
	
	/**
	 * return the health care episode of which this act is part
	 * @return
	 */
	public HealthCareEpisode getHealthCareEpisode();
	
	/**
	 * return the type of act
	 * 
	 * @return
	 */
	public HealthCareActType getActType();
	
	/**
	 * get the start date of the health care act
	 * @return
	 */
	public Instant getStartDate();
	
	/**
	 * sets the <code>startDate</code> for this health care act
	 * @param startDate
	 */
	public void setStartDate(Instant startDate);

	/**
	 * get the end date of the health care act
	 * @return
	 */
	public Instant getEndDate();
	
	/**
	 * sets the <code>endDate</code> for this health care act
	 * @param startDate
	 */
	public void setEndDate(Instant endDate);
	
	/**
	 * returns the provider of the health care act
	 * @return
	 */
	public HealthCareInstitution getProdiver();
	
	/**
	 * sets/modifies
	 * @param provider
	 */
	public void setProvider(HealthCareInstitution provider);
	
	/**
	 * Returns the health care professional who performed the act
	 */
	public HealthCareProfessional getPerformedBy();
	
	/**
	 * sets/modifies the the health care professional who performed this act to
	 * <code>healthCareProfessional</code>
	 * @param healthCareProfessional
	 */
	public void setPerformedBy(HealthCareProfessional healthCareProfessional);
	
	/**
	 * This is a shortcut. HealthCareAct's can be of different types, including tests, lab tests, ECGs,
	 * etc. We are "particularly" interested in healthcare acts that entail collecting ECGs, and thgus, instead
	 * of using a generic attribute 'tests', we are calling it  
	 * @return
	 */
	public Iterator<ECG> getECGs();

}
