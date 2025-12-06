/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;

/**
 * Author: Ghizlane Elboussaid & Hafedh Mili
 */
public interface HealthCondition {
	
	public Person getPerson();
	
	public Instant getDateOnset();
	
	public void setDateOnset(Instant date);
	
	public HealthConditionType getType();
	
	public void setType(HealthConditionType type);
	
	public SeverityLevel getSeverityLevel();
	
	public void setSeverityLevel(SeverityLevel severityLevel);
}