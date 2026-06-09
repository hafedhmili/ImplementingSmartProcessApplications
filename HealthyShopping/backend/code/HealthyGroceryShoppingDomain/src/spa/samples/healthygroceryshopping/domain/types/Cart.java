/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 * 
 * This class represents the physical cart in which the sopper will put
 * the food items. A cart has a collection of <code>CartItem</code>, which
 * have identifying tags, e.g. RFID tags that can be read by a sensor 
 * (e.g. RFID tag reader). It may have several such sensors: RFID tag
 * reader(s), a scale, etc.
 */
public interface Cart {
	
	/**
	 * Some sort of ID for the cart; a way to associate sensors with
	 * the cart
	 * @return
	 */
	public String getSerialNumber();
	
	/**
	 * returns the list of items in the cart
	 * @return
	 */
	public Iterator<CartItem> getItems();
	
	/**
	 * removes <code>item</code> from the cart. If the item
	 * was in the cart, it returns it. If the item was not in the
	 * cart, in the first place, it returns null
	 * @param item
	 * @return
	 */
	public CartItem removeItem(CartItem item);
	
	/**
	 * adds item to the cart. In addition to adding the item
	 * to the collection of items in the cart, this method will also
	 * associate the cart item with the corresponding 
	 * <code>ProductLineItem</code> 
	 * @param item
	 */
	public void addItem(CartItem item);
	
	/**
	 * returns the list of sensors attached to the cart
	 * @return
	 */
	public Iterator<Sensor> getSensors();
	
	/**
	 * adds (registers) a sensor to the cart
	 * @param sensor
	 */
	public void addSensor(Sensor sensor);

	/**
	 * removes <code>sensor</code> from the 
	 * cart. If <code>sensor</code> was not associated
	 * with the cart in the first place, it returns null.
	 * Else, it returns the argument.
	 * @param sensor
	 * @return
	 */
	public Sensor removeSensor(Sensor sensor);
}
