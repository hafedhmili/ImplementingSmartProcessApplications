package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

public interface HealthConditionEpisode {
	
	/**
	 * The health condition of which this is an episode
	 * @return
	 */
	public HealthCondition getHealthCondition();
	
	/**
	 * start date for the episode
	 * @return
	 */
	public Instant getStartDate();
	
	/**
	 * set/modify the start date for the episode
	 * @param startDate
	 */
	public void setStartDate(Instant startDate);

	/**
	 * end date for the episode
	 * @return
	 */
	public Instant getEndDate();
	
	/**
	 * set/modify the end date for the episode
	 * @param startDate
	 */
	public void setEndDate(Instant endDate);
	
	/**
	 * get the severity level of the current health condition episode
	 * @return
	 */
	public SeverityLevel getSeverityLebvel();
	
	/**
	 * set/modify the severity level of the current episode
	 * @param severityLevel
	 */
	public void setSeverityLevel(SeverityLevel severityLevel);
	
	/**
	 * return the latest health care episode for this condition episode
	 * @return
	 */
	public HealthCareEpisode getLatestCareEpisode();
	
	/**
	 * set the latest health care episode for this condition episode to
	 * <code>healthCareEpisode</code>
	 * @param healthCareEpisode
	 */
	public void setLatestCareEpisode(HealthCareEpisode healthCareEpisode);
	
	/**
	 * get the date of the latest health care episode
	 * @return
	 */
	public Instant getDateLatestCareEpisode();
	
	/**
	 * get the list of health care episodes for this condition episode
	 * @return
	 */
	public Iterator<HealthCareEpisode> getCareEpisodes();
	
	/**
	 * add <code>careEpisode</code> to the list of health care episodes
	 * for this condition episode
	 *  
	 * @param careEpisode
	 */
	public void addCareEpisode(HealthCareEpisode careEpisode);
	
	/**
	 * Remove  <code>careEpisode</code> from the list of health care episode
	 * of this condition episode.
	 * 
	 * @param careEpisode
	 * @return
	 */
	public HealthCareEpisode removeCareEpisode(HealthCareEpisode careEpisode);

}
