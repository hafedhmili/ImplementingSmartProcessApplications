/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * Interface that represents a shopping cart
 * 
 * In additional to accessing the attributes of a shopping cart (the person/shopper,
 * the physical cart, the product line items, and the cart score), it provides us
 * methods to manipulate the cart score (CRUDing nutritional parameters and
 * CartState)
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface ShoppingCart {
	
	/**
	 * the owner of the shopping cart (the shopper)
	 * @return
	 */
	public Person getPerson();
	
	/**
	 * the (representation of) physical cart
	 * @return
	 */
	public Cart getCart();
	
	/**
	 * modifies/sets the (representation of) physical cart
	 * @param cart
	 */
	public void setCart(Cart cart);
	
	/**
	 * returns the contents of the shopping cart
	 * @return
	 */
	public Iterator<ProductLineItem> getProductLineItems();
	
	/**
	 * Adds a new <code>ProductLineItem</code> for the product.
	 * Use the returned value to set the unit and the quantity
	 * @param product
	 * @return
	 */
	public ProductLineItem addLineItemForProduct(Product product);
	
	/**
	 * adds a <code>ProductLineItem</code> for <code>product</code>, <code>unit</code>, and
	 * initial <code>quantity</code>
	 * @param product
	 * @param unit
	 * @param quantity
	 * @return
	 */
	public ProductLineItem addLineItemForProduct(Product product, MeasurementUnit unit, float quantity);
	
	/**
	 * returns the <code>ProductLineItem</code> for the <code>product</code>
	 * @param product
	 * @return
	 */
	public ProductLineItem getProductLineItemForProduct(Product product);
	
	/**
	 * return the cart score
	 * @return
	 */
	public CartScore getScore();
	
	/**
	 * return the score for a specific nutritional parameter (e.g. sodium content)
	 * @param parameter
	 * @return
	 */
	public NutritionalParameterScore getScoreForParameter(NutritionalParameter parameter);
	
	/**
	 * set the <code>value</code> for the nutritional <code>parameter</code>
	 * @param parameter
	 * @param value
	 */
	public void setNutritionalScoreFor(NutritionalParameter parameter, ScoreValue value);
	
	/**
	 * return the list of nutritional scores
	 * @return
	 */
	public Iterator<NutritionalParameterScore> getNutritionalScores();
	
	/**
	 * add a nutritional score
	 * 
	 * @param parameterScore
	 */
	public void addNutritionalScore(NutritionalParameterScore parameterScore);
	
	/**
	 * remove a nutritional score
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
