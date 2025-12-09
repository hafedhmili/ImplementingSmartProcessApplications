/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import spa.samples.healthygroceryshopping.domain.types.Cart;
import spa.samples.healthygroceryshopping.domain.types.DomainObjectFactory;
import spa.samples.healthygroceryshopping.domain.types.Person;
import spa.samples.healthygroceryshopping.domain.types.PersonProfile;
import spa.samples.healthygroceryshopping.domain.types.ShoppingCart;

/**
 * Author: Ghizlane Boussaidi & Hafedh Mili
 */
public class PersonImpl implements Person {
	
	private String firstName;
	
	private String lastName;
	
	private PersonProfile profile;
	
	private Cart physicalCart;
	
	private ShoppingCart currentShoppingCart;
	
	private Collection<ShoppingCart> pastShoppingCarts;
	
	
	
	public PersonImpl(String firstName, String lastName, Instant birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile = DomainObjectFactory.getSingleton().createPersonProfile(this,birthdate);
		this.pastShoppingCarts = new ArrayList<>();
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public Instant getBirthdate() {
		return this.profile.getHealthProfile().getBirthdate();
	}

	@Override
	public ShoppingCart getCurrentShoppingCart() {
		return this.currentShoppingCart;
	}

	@Override
	public void setCurrentShoppingCart(ShoppingCart shoppingCart) {
		this.currentShoppingCart = shoppingCart;

	}

	@Override
	public Cart pickCart(String serialNumber) {
		// 1. Trey to get physical cart with serial number 
		Cart cart = DomainObjectFactory.getSingleton().getCartWithSerialNumber(serialNumber);
		// 2. if it is not null, create a shopping cart for it
		if (cart !=null) {
			this.physicalCart = cart;
			this.startShoppingCart(cart);
			return cart;
		}
		// 3. Else, return null
		
		return null;
	}

	@Override
	public ShoppingCart startShoppingCart(Cart cart) {
		ShoppingCart shoppingCart = DomainObjectFactory.getSingleton().createShoppingCart(this,cart);
		this.currentShoppingCart = shoppingCart;
		return shoppingCart;
	}

	@Override
	public Iterator<ShoppingCart> getPastCarts() {
		return this.pastShoppingCarts.iterator();
	}

	@Override
	public void archiveCurrentCart() {
		this.addPastCart(currentShoppingCart);
		this.currentShoppingCart = null;
		this.physicalCart = null;
	}

	@Override
	public void addPastCart(ShoppingCart shoppingCart) {
		this.pastShoppingCarts.add(shoppingCart);

	}

	@Override
	public ShoppingCart removePastCart(ShoppingCart pastCart) {
		if (this.pastShoppingCarts.remove(pastCart)) return pastCart;
		return null;
	}

	@Override
	public PersonProfile getProfile() {
		return this.profile;
	}

	@Override
	public Cart getPhysicalCart() {
		return this.physicalCart;
	}

}
