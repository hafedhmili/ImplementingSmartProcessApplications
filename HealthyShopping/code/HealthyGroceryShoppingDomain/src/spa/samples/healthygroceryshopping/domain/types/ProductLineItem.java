/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface ProductLineItem {
	
	/**
	 * returns the product. Need not be a food item
	 * @return
	 */
	public Product getProduct();
	
	/**
	 * returns the measurement unit, that helps us interpret
	 * the quality (if quantity = 2, does that mean two kilograms,
	 * two pounds, or two units)
	 * @return
	 */
	public MeasurementUnit getUnit();
	
	/**
	 * set the unit of measurement for this product
	 * @param unit
	 */
	public void setMeasurementUnit(MeasurementUnit unit);
	
	/**
	 * Set the quantity for the product
	 * @param quantity
	 */
	public void setQuantity(float quantity);
	
	/**
	 * return the quantity
	 * @return
	 */
	public float getQuantity();

}
