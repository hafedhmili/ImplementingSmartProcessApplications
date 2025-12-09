/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * This is the interrace that represents users of the application
 * (consumers). A person has a profile, which includes a 
 * health profile, and a dietary profile, representing the person's
 * dietary preferences.
 * 
 * When we create a person, we create the profile, including the 
 * dietary and health profiles
 * Author: Ghizlane ELboussaidi & Hafedh Mili
 */
public interface Person {
	
	public String getFirstName();
	
	public String getLastName();
	
	public Instant getBirthdate();
	
	public ShoppingCart getCurrentShoppingCart();
	
	public void setCurrentShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * 
	 * This method assigns to the person the cart whose serial number is 
	 * <code>serialNumber</code>.
	 * We can think of it as the customer grabbing a cart. The proximity of the
	 * cart to an identifying item of the customer (e.g. cell phone
	 * or smart watch) would link the two.
	 * 
	 * @param serialNumber
	 * @return
	 */
	public Cart pickCart(String serialNumber);
	
	/**
	 * returns the current physical cart--if any
	 * @return
	 */
	public Cart getPhysicalCart();
	
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
	
	/**
	 * returns the person's profile
	 * @return
	 */
	public PersonProfile getProfile();
	

}
