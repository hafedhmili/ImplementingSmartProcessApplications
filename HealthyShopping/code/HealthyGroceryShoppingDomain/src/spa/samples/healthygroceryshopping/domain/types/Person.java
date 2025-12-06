/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface Person {
	
	public String getFirstName();
	
	public String getLastName();
	
	public ShoppingCart getCurrentShoppingCart();
	
	public void setCurrentShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * the person walks into a store and grabs a (physical cart). This will
	 * start a shopping session
	 * @param cart
	 * @return
	 */
	public ShoppingCart startShoppingCart(Cart cart);
	
	public Iterator<ShoppingCart> getPastCarts();
	
	/**
	 * Add the current cart to the list of past carts
	 */
	public void archiveCurrentCart();

	/**
	 * Past carts are supposed to result from archiving current carts, when a 
	 * shopping episode ends, but we end this method for convenience/commodity
	 * @param shoppingCart
	 */
	public void addPastCart(ShoppingCart shoppingCart);
	
	public ShoppingCart removePastCart(ShoppingCart pastCart);
	
	public PersonProfile getProfile();
	

}
