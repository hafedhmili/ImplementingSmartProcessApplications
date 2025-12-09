/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.impl;

import java.time.Instant;
import java.util.HashMap;

import spa.samples.healthygroceryshopping.domain.types.Cart;
import spa.samples.healthygroceryshopping.domain.types.CartItem;
import spa.samples.healthygroceryshopping.domain.types.CartScore;
import spa.samples.healthygroceryshopping.domain.types.DietaryPreference;
import spa.samples.healthygroceryshopping.domain.types.DomainObjectFactory;
import spa.samples.healthygroceryshopping.domain.types.FoodProduct;
import spa.samples.healthygroceryshopping.domain.types.FoodProductCategory;
import spa.samples.healthygroceryshopping.domain.types.FoodProductItem;
import spa.samples.healthygroceryshopping.domain.types.FoodProductNutritionalInfo;
import spa.samples.healthygroceryshopping.domain.types.HealthCondition;
import spa.samples.healthygroceryshopping.domain.types.HealthConditionType;
import spa.samples.healthygroceryshopping.domain.types.MeasurementUnit;
import spa.samples.healthygroceryshopping.domain.types.NutritionalIndication;
import spa.samples.healthygroceryshopping.domain.types.NutritionalInfoAuthority;
import spa.samples.healthygroceryshopping.domain.types.NutritionalParameter;
import spa.samples.healthygroceryshopping.domain.types.NutritionalParameterScore;
import spa.samples.healthygroceryshopping.domain.types.NutritionalParameterValue;
import spa.samples.healthygroceryshopping.domain.types.Person;
import spa.samples.healthygroceryshopping.domain.types.PersonProfile;
import spa.samples.healthygroceryshopping.domain.types.Product;
import spa.samples.healthygroceryshopping.domain.types.ProductLineItem;
import spa.samples.healthygroceryshopping.domain.types.Range;
import spa.samples.healthygroceryshopping.domain.types.ScoreValue;
import spa.samples.healthygroceryshopping.domain.types.Sensor;
import spa.samples.healthygroceryshopping.domain.types.SensorModel;
import spa.samples.healthygroceryshopping.domain.types.SensorType;
import spa.samples.healthygroceryshopping.domain.types.ShoppingCart;
import spa.samples.healthygroceryshopping.domain.types.Tag;

/**
 * This factory class has factory methods for all implementation classes.
 * 
 * It should be used by both "client code", and by implementation classes themselves
 * so that implementation classes do not depend on each other
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public class DomainObjectFactoryImpl implements DomainObjectFactory {
	
	private static DomainObjectFactoryImpl singleton = null;
	
	private HashMap<String,Cart> physicalCarts;
	
	DomainObjectFactoryImpl() {
		physicalCarts = new HashMap<>();
	}
	
	public static DomainObjectFactoryImpl getSingleton() {
		if (singleton == null) singleton = new DomainObjectFactoryImpl();
		return singleton;
	}

	@Override
	public Cart createCart(String serialNumber) {
		Cart cart = new CartImpl(serialNumber);
		this.physicalCarts.put(serialNumber, cart);
		return cart;
	}

	@Override
	public CartItem createCartItem(Tag tag, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartScore createCartScore(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DietaryPreference createDietaryPreference(FoodProduct foodInQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createProduct(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person createPerson(String firstName, String lastName, Instant birthDate) {
		return new PersonImpl(firstName,lastName,birthDate);
	}

	@Override
	public FoodProduct createFoodProduct(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodProductCategory createFoodProductCategory(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodProductItem createFoodProductItem(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodProductNutritionalInfo createFoodProductNutritionalInfo(FoodProduct foodProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthCondition createHealthCondition(Person person, HealthCondition type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthCondition createHealthCondition(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthConditionType createHealthConditionType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalIndication createNutritionalIndication(NutritionalParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalInfoAuthority createNutritionalInfoAuthority(String name, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalParameter createNutritionalParameter(String name, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalParameterScore createNutritionalParameterScore(NutritionalParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalParameterScore createNutritionalParameterScore(NutritionalParameter parameter, ScoreValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalParameterValue createNutritionalParameterValue(NutritionalParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutritionalParameterValue createNutritionalParameterValue(NutritionalParameter parameter,
			MeasurementUnit unit, Range value, MeasurementUnit referenceUnit, Range referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person createPerson(String firstName, String lastName, String snn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductLineItem createProductLineItem(Product product, MeasurementUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor createSensor(SensorModel model, String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorModel createSensorModel(SensorType type, String modelNumber, String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart createShoppingCart(Person person, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag createTag(Product product, String inventoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonProfile createPersonProfile(Person person, Instant birthdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartWithSerialNumber(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
