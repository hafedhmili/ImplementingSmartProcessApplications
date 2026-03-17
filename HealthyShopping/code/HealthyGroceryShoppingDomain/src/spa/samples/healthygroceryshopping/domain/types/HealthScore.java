/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This interface represents the object that centralizes the results
 * of evaluating the "fitness" of a food item or the entire caret.
 * 
 * This is the entity where we centralize the nutritional info, and the health score
 * 
 * The NutritionalParameterValue's are just computed/looked up, with no judgement. For example,
 * to say that a food item has 11 g protein is neutral.
 * 
 * The business rules will then assess whether those numbers are healthy for the given 
 * shopper, and that is the HealthParameterScore, which is a qualitative judgement
 * 
 * Author: Ghizlane ElBoussaidi & Hafedh Mili
 */
public interface HealthScore {
	
	/**
	 * return the list of nutritional value
	 * @return
	 */
	public Iterator<NutritionalParameterValue> getNutritionalParameterValues();
	
	/**
	 * add a nutritional parameter value.
	 * 
	 * @param parameterValue
	 */
	public void addNutritionalParameterValue(NutritionalParameterValue parameterValue);
	
	/**
	 * remove a nutritional parameter value
	 * 
	 * @param parameterValue
	 * @return
	 */
	public NutritionalParameterValue removeNutritionalParemeterValue(NutritionalParameterValue parameterValue);
	
	/**
	 * return the list of health parameter values
	 * @return
	 */
	public Iterator<HealthParameterScore> getHealthParameterScores();
	
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
