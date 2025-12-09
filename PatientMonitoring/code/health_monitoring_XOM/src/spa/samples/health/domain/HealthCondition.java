package spa.samples.health.domain;

import java.util.Iterator;

/**
 * A HealthCondition is a state of being that concerns health,
 * and that is worth knowing about a person/patient.
 * 
 * It can be a pathology/disease, an injury, or simply a 
 * statistically significant (i.e. "away" from normal)  condition
 * (e.g. weight, height, or rates of various chemicals in the body)
 * @author hafedhmili
 *
 */
public interface HealthCondition {
	
	
	
	/**
	 * The type of condition. It could be the name of a
	 * injury, disease, infirmity, etc
	 * @return
	 */
	public HealthConditionType getType();
	
	/**
	 * returns the severity of the condition
	 * @return
	 */
	public Severity getSeverity();
	
	
	/**
	 * returns the history of the health condition. The history is a series of
	 * <code>HealthEpisode</code>s, 
	 * @return
	 */
	public Iterator<HealthEpisode> getHistory();
	
	/**
	 * returns the one health episode that corresponds to the 
	 * onset of this health condition. It is the first element
	 * )chronologically) of the history. It could be the first time
	 * the condition was diagnosed/identified
	 * @return
	 */
	public HealthEpisode getOnset();
	
	/**
	 * return the patient
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * adds a health episode to the health condition
	 * @param episode
	 */
	public void addHealthEpisode(HealthEpisode episode);

}
