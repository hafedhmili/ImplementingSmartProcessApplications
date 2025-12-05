/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * This interface represents the tag that is attached to the items
 * put in a physical shopping cart (represented by the interface <code>Cart</code>).
 * 
 * This is the tag that will be sensed by the sensors attached to the cart. It could be
 * an RFID tag.
 * 
 * We can think of it as including at least two pieces of information: 1) it identifies the product, and
 * 2) <i>may</i> included an inventory id that may distinguish one item from another. For example,
 * if the shopper puts in the cart a coffee machine, the tag will identify the coffee machine model, and
 * a serial number
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface Tag {
	
	/**
	 * this function returns the <code>Product</code> for which the cart item is
	 * an instance. To use the example of the coffee machine, it returns the model of 
	 * the coffee machine.
	 * @return
	 */
	public Product getProduct();
	
	/**
	 * returns the inventory ID of the cart item to which this tag is attached.
	 * For small or bulk products, the value will be null
	 * @return
	 */
	public String getInventoryId();

}
