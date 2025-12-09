/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * This class represents the physical objects that we put in a physical cart.
 * 
 * A CartItem can be a bottle of dishwashing liquid, with a bar code, and an RFID tag,
 * a cereal box, with a bar code, and an RFID tag, or a bag of
 * bulk tomatoes, picked by the shopper, put in a plastic (or compostable) bag, and 
 * weighed at a weighing station which generated a custom sticker, including an RFID tag.
 * 
 * Once a CartItem is placed in a cart, the cart sensors will read the tag and figure out
 * the associated <code>FoodProductItem</code>, and associates it with the corresponding 
 * <code>ProductLineItem</code> of the shopping cart
 * 
 * Author: Ghizlane Elboussaidi &Hafedh Mili
 */
public interface CartItem {
	
	/**
	 * Get the cart into which the item was inserted
	 * @return
	 */
	public Cart getCart();
	
	/**
	 * returns the tag attached to the product. The tag may contain several data items
	 * @return
	 */
	public Tag getTag();
	
	/**
	 * returns the corresponding product. The first time this function
	 * is called, it finds the info from the tag, and stores it.
	 * Subsequently, the function returns the stored value.
	 * 
	 * @return
	 */
	public Product getProduct();
	
	/**
	 * returns the corresponding product line item
	 * @return
	 */
	public ProductLineItem getProductLineItem();
	
	/**
	 * this method associates a CartItem with a ProductLineItem of the 
	 * (conceptual) ShoppingCart.
	 * @param productLineItem
	 */
	public void setProductLineItem(ProductLineItem productLineItem);

}
