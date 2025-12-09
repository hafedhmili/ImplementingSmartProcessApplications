package spa.samples.health.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

/**
 * A HealthEpisode is an element of the history of a HealthCondition
 * In other words, the history of a HealthCondition is a (time?) sequence
 * of HealthEpisode s. An episode has an occurrence or start time, and
 * possibly an end date--and thus duration. An episode can be the onset
 * of the condition. For example, if it is an infection, the onset could
 * a skin injury, or may be a hospitalization for another health condition,
 * or an accident, etc. Another episode could be exacerbation ... or treatment, or
 * remission, or cure.
 * 
 * A HealthAct is a special case of an episode, and it is something performed
 * by a health professional. See <code>HealthAct</code> 
 *
 * @author hafedhmili
 *
 */
public interface HealthEpisode {
	
	/**
	 * return the patient concerned by the episode
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * The episode type
	 * @return
	 */
	public HealthEpisodeType getEpisodeType();
	
	/**
	 * return the start date of the episode
	 * @return
	 */
	public Instant getStartDate();
	
	/**
	 * the end date of the episode.
	 * An episode may be ongoing, in which case
	 * there is no end date
	 * @return
	 */
	public Instant getEndDate();
	
	/**
	 * returns the duration of the episode, which is the
	 * difference between the start date and the end date. 
	 * If the episode is still ongoing, than the duration
	 * is the difference between today's date and the start date
	 * @return
	 */
	public Duration  getDuration();

	/**
	 * return the severity of the episode. It is relevant to some,
	 * but not all, types of HealthEpisodes
	 * @return
	 */
	public Severity getSeverity();
	
	/**
	 * Returns the readings associated with the health episode. These
	 * could be the results of tests performed at different times
	 * during the episode. The episode could, itself, be about taking
	 * these readings--if it is HealthAct.
	 * @return
	 */
	public Iterator<Reading> getReadings();
	
	/**
	 * Add <code>reading</code> to the list of readings associated
	 * with the health episode
	 * @param reading
	 */
	public void addAssociatedReading(Reading reading);
	
	/**
	 * Remove <code>reading</code> from the list of readings associated
	 * with the health episode
	 * @param reading
	 */
	public void removeAssociatedReading(Reading reading);
}
