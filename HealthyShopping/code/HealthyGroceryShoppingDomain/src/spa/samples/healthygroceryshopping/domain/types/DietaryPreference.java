/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface DietaryPreference {
	
	public FoodProduct getFoodProduct();
	
	public void setFoodProduct(FoodProduct product);
	
	public PreferenceModality getPreference();
	
	public void setPreference(PreferenceModality modality);

}
