/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 * 
 * A Person's profile consists of "private information", including ssn, address,
 * a health profile, and a dietary profile. When we create a person, we create a profile,
 * with an empty health profile, and an empty dietary profile, which can be populated
 * later. But we do not "assign" new HealthProfile or a new DietaryProfile to an existing person
 * profile, we can just add to/remove from it.
 * 
 * We provide a bunch of methods that will actually delegate to the health and dietary
 * profiles
 */
public interface PersonProfile {
	
	public String getSSN();
	
	public Address getAddress();
	
	public void setAddress(Address adresse);
	
	/**
	 * returns the health profile component of the person profile
	 * @return
	 */
	public HealthProfile getHealthProfile();
	
	/**
	 * returns the dietary profile component of the person profile
	 * @return
	 */
	public DietaryProfile getDietrayProfile();
	
	/**
	 * adds he health condition to the <code>healthProfile</code> component of the profile
	 * @param healthCondition
	 */
	public void addHealthCondition(HealthCondition healthCondition);
	
	/**
	 * remove the health condition from the <code>healthProfile</code> component of the profile
	 * @param healthCondition
	 */
	public void removeHealthCondition(HealthCondition healthCondition);
	
	/**
	 * return the list of health conditions in the <code>healthProfile</code> component of the profile
	 * @param healthCondition
	 */
	public Iterator<HealthCondition> getHealthConditions();
	
	/**
	 * adds a dietary preference to the profile of the person. This navigates to the
	 * dietary component of the person profile to make the addition
	 * @param preference
	 */
	public void addDietaryPreference(DietaryPreference preference);
	
	/**
	 * removes a the dietary preference <code>preference</code> from the dietary profile
	 * of the person profile.
	 * 
	 * @param preference
	 * @return
	 */
	public DietaryPreference removeDietaryPreference(DietaryPreference preference);
	
	/**
	 * return the list of dietary preferences of the person
	 * @return
	 */
	public Iterator<DietaryPreference> getDietaryPreferences();
	
	

}
