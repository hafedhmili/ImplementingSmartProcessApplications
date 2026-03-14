/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This interface represents the object that centralizes the results
 * of evaluating the "fitness" of the shopping cart to the shopper.
 * 
 * This is the entity where we centralize the nutritional info, which
 * is an aggregate of the contents of the cart, along with the final
 * overall <code>carState</code>, which is the result of the 
 * business rule processing.
 * 
 * We can score a shopping cart relative to sodium content, trans fats, sugar,
 * processed foods, pesticides, etc. (all instances of <code>NutritionalParameterScore</code>)
 * 
 * Author: Ghizlane ElBoussaidi & Hafedh Mili
 */
public interface HealthScore {
	
	/**
	 * return the list of nutritional value
	 * @return
	 */
	public Iterator<NutritionalParameterValue> getNutritionalValues();
	
	/**
	 * add a nutritional value.
	 * 
	 * @param parameterValue
	 */
	public void addNutritionalValue(NutritionalParameterValue parameterValue);
	
	/**
	 * remove a nutritional vaue
	 * 
	 * @param parameterValue
	 * @return
	 */
	public NutritionalParameterScore removeNutritionalScore(NutritionalParameterValue parameterValue);
	
	/**
	 * return the list of nutritional value
	 * @return
	 */
	public Iterator<HealthParameterScore> getHealthScore();
	
	/**
	 * add a health parameter score.
	 * 
	 * @param parameterScore
	 */
	public void addHealthParameterScore(HealthParameterScore parameterScore);
	
	/**
	 * remove a health parameter score
	 * 
	 * @param parameterScore
	 * @return
	 */
	public HealthParameterScore removeHealthParameterScore(HealthParameterScore parameterScore);
}
