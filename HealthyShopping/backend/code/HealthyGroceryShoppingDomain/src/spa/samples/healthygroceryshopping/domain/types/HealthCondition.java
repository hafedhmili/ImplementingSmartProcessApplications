/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;

/**
 * This interface represents users' health condition.
 * 
 * A health condition concerns a <code>person</code>, is of a given
 * <code>type</code>, was diagnosed on a certain <code>dateOnset</code>,
 * and has <code>severityLevel</code>. Ethymologically, "dateOnset" would
 * mean when the condition started. However, most health condition are
 * progressive, and so for all practical purposes dateOnset can be
 * equated to date of diagnosis
 * 
 * John Smith's "high blood pressure condition", has 
 * <code>person</code> "John Smith", is of <code>type</code>, High_Blood_Pressure
 * was diagnosed on <code>dateOnset</code> = October 2024,
 * and has <code>severityLevel</code> MILD
 * 
 * Author: Ghizlane Elboussaid & Hafedh Mili
 */
public interface HealthCondition {
	
	/**
	 * the person in question
	 * @return
	 */
	public Person getPerson();
	
	/**
	 * the health condition type in question
	 * @return
	 */
	public HealthConditionType getType();
	
	/**
	 * the date on which it was onset or diagnosed
	 * @return
	 */
	public Instant getDateOnset();
	
	/**
	 * set the onset (or diagnosis) date
	 * @param date
	 */
	public void setDateOnset(Instant date);
	
	/**
	 * severity level of condition
	 * @return
	 */
	public SeverityLevel getSeverityLevel();
	
	/**
	 * modify the severity level
	 * @param severityLevel
	 */
	public void setSeverityLevel(SeverityLevel severityLevel);
}