/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane ElBoussaidi & Hafedh Mili
 */
public interface CartScore {
	
	public Cart getCart();
	
	public Iterator<NutritionalParameterScore> getNutritionalScores();
	
	public void addNutritionalScore(NutritionalParameterScore parameterScore);
	
	public NutritionalParameterScore removeNutritionalScore(NutritionalParameterScore parameterScore);
	
	public CartState getCartState();
	
	public void setCartState(CartState state);
}
