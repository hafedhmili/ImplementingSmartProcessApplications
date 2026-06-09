package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

public interface HealthCareEpisode {
	
	/**
	 * return the health condition episode of which this is a health care 
	 * episode
	 * 
	 * @return
	 */
	public HealthConditionEpisode getHealthConditionEpisode();
	
	/**
	 * return the start date of this care episode (e.g. first day of 
	 * hospitalization)
	 * 
	 * @return
	 */
	public Instant getStartDate();
	
	/**
	 * set/modify the start date for this episode
	 * @param startDate
	 */
	public void setStartDate(Instant startDate);

	/**
	 * return the end date of this care episode (e.g. first day of 
	 * hospitalization)
	 * 
	 * @return
	 */
	public Instant getEndDate();
	
	/**
	 * set/modify the end date for this episode
	 * @param startDate
	 */
	public void setEndDate(Instant endDate);
	
	/**
	 * return the latest/most recent health care act performed within this
	 * care episode
	 * @return
	 */
	public HealthCareAct getLatestCareAct();
	
	/**
	 * sets/modifies the latest health care act
	 * 
	 * @param healthCareAct
	 */
	public void setLatestCareAct(HealthCareAct healthCareAct);
	
	/**
	 * returns the (start) date of the latest care act
	 * @return
	 */
	public Instant getDateLatestAct();
	
	/**
	 * get the list of health care acts performed within this care episode
	 * @return
	 */
	public Iterator<HealthCareAct> getCareActs();
	
	/**
	 * Add <code>healthCareAct</code> to the list of health care acts
	 * 
	 * @param healthCareAct
	 */
	public void addCareAct(HealthCareAct healthCareAct);
	
	/**
	 * remove <code>healthCareAct</code> from the list of health care
	 * acts
	 * 
	 * @param healthCareAct
	 * @return
	 */
	public HealthCareAct removeCareAct(HealthCareAct healthCareAct);
}
