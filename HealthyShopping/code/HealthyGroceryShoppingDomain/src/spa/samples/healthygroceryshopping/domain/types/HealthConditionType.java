/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * This interface represents health condition types (e.g. "High blood pressure", or "diabetes").
 * 
 * Person will have instances of these condition types (Hafedh Mili's 'high bllod pressure').
 * 
 * They have a name, a description, subtypes, and nutritional indications
 * 
 * Author: Ghizlane Elboussaidi Hafedh Mili
 */
public interface HealthConditionType {
	
	/**
	 * return the name
	 * @return
	 */
	public String getName();
	
	/**
	 * return the description
	 * @return
	 */
	public String getDescription();
	
	/**
	 * set the description
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * return the subtypes
	 * @return
	 */
	public Iterator<HealthConditionType> getSubtypes();
	
	/**
	 * add <code>subtype</code>
	 * @param subtype
	 */
	public void addSubtype(HealthConditionType subtype);
	
	/**
	 * remove <code>subtype</code>
	 * 
	 * @param subtype
	 * @return
	 */
	public HealthConditionType removeSubtype(HealthConditionType subtype);
	
	/**
	 * return the list of nutritional indications
	 * @return
	 */
	public Iterator<NutritionalIndication> getNutritionalIndications();
	
	/**
	 * Add a nutritional <code>indication</code>
	 * @param indication
	 */
	public void addNutritionalIndication(NutritionalIndication indication);
	
	/**
	 * remove a nutritional <code>indication</code>
	 * @param indication
	 * @return
	 */
	public NutritionalIndication removeNutritionalIndication(NutritionalIndication indication);

}
