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
public interface CartScore {
	
	/**
	 * return the corresponding shopping cart
	 * @return
	 */
	public ShoppingCart getShoppingCart();
	
	/**
	 * return the list of nutritional scores
	 * @return
	 */
	public Iterator<NutritionalParameterScore> getNutritionalScores();
	
	/**
	 * add a nutritional score.
	 * 
	 * @param parameterScore
	 */
	public void addNutritionalScore(NutritionalParameterScore parameterScore);
	
	/**
	 * remove a nutritional score
	 * 
	 * @param parameterScore
	 * @return
	 */
	public NutritionalParameterScore removeNutritionalScore(NutritionalParameterScore parameterScore);
	
	/**
	 * return the cart state
	 * @return
	 */
	public CartState getCartState();
	
	/**
	 * set the cart state
	 * @param state
	 */
	public void setCartState(CartState state);
}
