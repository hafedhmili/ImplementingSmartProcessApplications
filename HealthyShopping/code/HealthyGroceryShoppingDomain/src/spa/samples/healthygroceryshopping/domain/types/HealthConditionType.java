/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface HealthConditionType {
	
	public String getName();
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public Iterator<HealthConditionType> getSubtypes();
	
	public void addSubtype(HealthConditionType subtype);
	
	public HealthConditionType removeSubtype(HealthConditionType subtype);
	
	public Iterator<NutritionalIndication> getNutritionalIndications();
	
	public void addNutritionalIndication(NutritionalIndication indication);
	
	public NutritionalIndication removeNutritionalIndication(NutritionalIndication indication);

}
