/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;

import spa.samples.healthygroceryshopping.domain.impl.DomainObjectFactoryImpl;
import spa.samples.healthygroceryshopping.domain.impl.PersonImpl;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface DomainObjectFactory {
	
	public Cart createCart(String serialNumber);
	
	public CartItem createCartItem(Tag tag, Cart cart);
	
	public CartScore createCartScore(Cart cart);
	
	public DietaryPreference createDietaryPreference(FoodProduct foodInQuestion);
	
	public Product createProduct(String name);
	
	public Person createPerson(String firstName, String lastName, Instant birthDate);
	
	public FoodProduct createFoodProduct(String name);
	
	public FoodProductCategory createFoodProductCategory(String name);
	
	public FoodProductItem createFoodProductItem(String name);
	
	public FoodProductNutritionalInfo createFoodProductNutritionalInfo(FoodProduct foodProduct);
	
	public HealthCondition createHealthCondition(Person person, HealthCondition type);
	
	public HealthCondition createHealthCondition(Person person);
	
	public HealthConditionType createHealthConditionType(String name);
	
	public NutritionalIndication createNutritionalIndication(NutritionalParameter parameter);
	
	public NutritionalInfoAuthority createNutritionalInfoAuthority(String name, String description);
	
	public NutritionalParameter createNutritionalParameter(String name, String description);
	
	public NutritionalParameterScore createNutritionalParameterScore(NutritionalParameter parameter);
	
	public NutritionalParameterScore createNutritionalParameterScore(NutritionalParameter parameter, ScoreValue value);

	public NutritionalParameterValue createNutritionalParameterValue(NutritionalParameter parameter);
	
	public NutritionalParameterValue createNutritionalParameterValue(NutritionalParameter parameter, MeasurementUnit unit, Range value, MeasurementUnit referenceUnit, Range referenceValue);
	
	public Person createPerson(String firstName, String lastName, String snn);
	
	public ProductLineItem createProductLineItem(Product product, MeasurementUnit unit);
	
	public Sensor createSensor(SensorModel model, String serialNumber);
	
	public SensorModel createSensorModel(SensorType type, String modelNumber, String modelName);
	
	public ShoppingCart createShoppingCart(Person person, Cart cart);
	
	public Tag createTag(Product product,String inventoryId);

	/**
	 * @return
	 */
	public static DomainObjectFactory getSingleton() {
		return DomainObjectFactoryImpl.getSingleton();
	}

	/**
	 * Creates a person profile, given the person object and their birthdate
	 * @param person
	 * @param birthdate
	 * @return
	 */
	public PersonProfile createPersonProfile(Person person, Instant birthdate);

	/**
	 * 
	 * This method returns the "physical cart" associated with a given serial number. This assumes that
	 * the factory holds a dictionary of physical carts, known by serial number
	 * 
	 * @param serialNumber
	 * @return
	 */
	public Cart getCartWithSerialNumber(String serialNumber);	
	
}
