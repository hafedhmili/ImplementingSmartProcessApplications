/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import spa.samples.healthygroceryshopping.domain.types.Cart;
import spa.samples.healthygroceryshopping.domain.types.CartItem;
import spa.samples.healthygroceryshopping.domain.types.Sensor;

/**
 * Author: Hafedh Mili
 */
public class CartImpl implements Cart {
	
	private String serialNumber;
	
	private Collection<Sensor> sensors;
	
	private Collection<CartItem> cartItems;
	
	public CartImpl(String serialNumber) {
		this.serialNumber = serialNumber;
		sensors = new ArrayList<>();
		cartItems = new ArrayList<>();
	}

	@Override
	public String getSerialNumber() {
		return serialNumber;
	}

	@Override
	public Iterator<CartItem> getItems() {
		return cartItems.iterator();
	}

	@Override
	public CartItem removeItem(CartItem item) {
		if (cartItems.remove(item)) return item;
		return null;
	}

	@Override
	public void addItem(CartItem item) {
		cartItems.add(item);

	}

	@Override
	public Iterator<Sensor> getSensors() {
		return sensors.iterator();
	}

	@Override
	public void addSensor(Sensor sensor) {
		sensors.add(sensor);

	}

	@Override
	public Sensor removeSensor(Sensor sensor) {
		if (sensors.remove(sensor)) return sensor;
		return null;
	}

}
