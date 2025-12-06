/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface ShoppingCart {
	
	public Person getPerson();
	
	public Cart getCart();
	
	public void setCart(Cart cart);
	
	public Iterator<ProductLineItem> getProductLineItems();
	
	public ProductLineItem addLineItemForProduct(Product product);
	
	public void addProductLineItem(ProductLineItem item);
	
	public CartScore getScore();
	
	public NutritionalParameterScore getScoreForParameter(NutritionalParameter parameter);
	
	public void setNutritionalScoreFor(NutritionalParameter parameter, ScoreValue value);
	
	public Iterator<NutritionalParameterScore> getNutritionalScores();
	
	public void addNutritionalScore(NutritionalParameterScore parameterScore);
	
	public NutritionalParameterScore removeNutritionalScore(NutritionalParameterScore parameterScore);
	
	public CartState getCartState();
	
	public void setCartState(CartState state);

}
