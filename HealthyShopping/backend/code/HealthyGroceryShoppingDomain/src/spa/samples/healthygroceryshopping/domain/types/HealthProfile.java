/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * 
 * This interface represents the health profile. This is the place where we
 * store the birthdate (the birthdate should not be of interest elsewhere).
 * 
 * It also contains the list of healtconditions
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface HealthProfile {
	
	/**
	 * return the object in question
	 * @return
	 */
	public Person getPerson();
	
	/**
	 * return the birthdate
	 * @return
	 */
	public Instant getBirthdate();
	
	/**
	 * set the birthdate
	 * @param birthdate
	 */
	public void setBirthdate(Instant birthdate);
	
	/**
	 * adds <code>healthCondition</code>
	 * 
	 * @param healthCondition
	 */
	public void addHealthCondition(HealthCondition healthCondition);
	
	/**
	 * Adds a health condition to the health profile by specifying its components
	 * @param type
	 * @param onsetDate
	 * @param severityLevel
	 * @return
	 */
	public HealthCondition addHealthCondition(HealthConditionType type, Instant onsetDate, SeverityLevel severityLevel);
	
	/**
	 * removes the health <code>condition</code>
	 * @param condition
	 * @return
	 */
	public HealthCondition removeHealthCondition(HealthCondition condition);
	
	/**
	 * return the list of health conditions
	 * @return
	 */
	public Iterator<HealthCondition> getHealthConditions();
	
	/**
	 * returns the list of health conditions of a specific type.
	 * 
	 * For example, a health condition could be broken bones. I can
	 * break my legs twice.
	 * @param type
	 * @return
	 */
	public Iterator<HealthCondition> getHealthConditionsOfType(HealthConditionType type);
}
