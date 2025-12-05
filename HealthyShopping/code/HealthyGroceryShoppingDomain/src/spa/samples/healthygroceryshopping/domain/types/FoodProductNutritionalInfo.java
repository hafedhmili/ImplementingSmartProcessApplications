/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface FoodProductNutritionalInfo {
	
	public FoodProduct getFood();
	
	public Instant getInfoDate();
	
	public NutritionalInfoAuthority getNutritionalInfoAuthority();
	
	public void setNutritionalInfoAuthority(NutritionalInfoAuthority authority);
	
	public Iterator<NutritionalParameterValue> getNutritionalParameterValues();
	
	public void addNutritionalParameterValue(NutritionalParameterValue npValue);
	
}
