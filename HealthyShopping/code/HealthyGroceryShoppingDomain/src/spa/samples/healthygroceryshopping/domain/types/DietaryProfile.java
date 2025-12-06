/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface DietaryProfile {
	
	public void addDietaryPreference(DietaryPreference preference);
	
	public DietaryPreference removeDietaryPreference(DietaryPreference preference);
	
	public Iterator<DietaryPreference> getPreferences();


}
