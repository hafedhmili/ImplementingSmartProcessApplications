/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This interface represents the dietary profile. It consists of a bunch
 * of dietary preferences
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface DietaryProfile {
	
	/**
	 * return the person in question
	 * @return
	 */
	public Person getPerson();
	
	/**
	 * add a <code>DietaryPreference</code> object passed as
	 * argument
	 * 
	 * @param preference
	 */
	public void addDietaryPreference(DietaryPreference preference);
	
	/**
	 * add (create) a dietary preference, specified through the food product and
	 * the preference modality
	 * 
	 * @param foodProduct
	 * @param preference
	 * @return
	 */
	public DietaryPreference addDietaryPreference(FoodProduct foodProduct, PreferenceModality preference);
	
	/**
	 * remove the dietary <code>preference</code>
	 * @param preference
	 * @return
	 */
	public DietaryPreference removeDietaryPreference(DietaryPreference preference);
	
	/**
	 * remove the dietary preference for food product <code>product</code>. We assume here
	 * that we have one preference per product
	 * 
	 * @param product
	 * @return
	 */
	public DietaryPreference removeDietaryPreferenceForProduct(FoodProduct product);
	
	/**
	 * 
	 * Return the list of dietary preferences
	 * 
	 * @return
	 */
	public Iterator<DietaryPreference> getPreferences();


}
