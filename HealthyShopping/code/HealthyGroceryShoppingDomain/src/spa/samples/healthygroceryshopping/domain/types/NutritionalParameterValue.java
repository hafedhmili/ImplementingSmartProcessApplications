/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * This is the interface that represent the nutritional info
 * about some nutritional element for a particular food item or 
 * food category.
 * 
 * For example, the back of my Los Cantores Lime Tortilla Chips shows
 * that the protein content (<code>nutritionalParameter</code>, given in
 * grams (<code>unit</code> is 3 (<code>value</code>, which can be
 * represented by the range [3,3]), per 50 (<code>referenceValue</code>)
 * grams (<code>referenceUnit</code>).
 * Author: Hafedh Mili
 */
public interface NutritionalParameterValue {
	
	/**
	 * return the nutritional parameter (e.g. sodium content) for
	 * which this is a value
	 * @return
	 */
	public NutritionalParameter getNutritionalParameter();
	
	/**
	 * return the unit of measurement for this parameter. For
	 * example, it is milligrams for Potassium, and grams for 
	 * carbohydrates
	 * @return
	 */
	public MeasurementUnit getUnit();
	
	/**
	 * set the measurement unit
	 * @param unit
	 */
	public void setUnit(MeasurementUnit unit);
	
	/**
	 * get the value, as a range. Ranges are very helpful for
	 * food product categories
	 * @return
	 */
	public Range getValue();
	
	/**
	 *  set the value
	 * @param interval
	 */
	public void setValue(Range interval);
	

	/**
	 * return the unit of measurement for the reference quantity. For my box
	 * of tortillas, the reference unit is grams (of tortillas)
	 * @return
	 */
	public MeasurementUnit getReferenceUnit();
	
	/**
	 * set the unit of measurement for the reference. For the For the example 
	 * mentioned in the class header, the reference unit is gram
	 * @param unit
	 */
	public void setReferenceUnit(MeasurementUnit unit);
	
	/**
	 * return the reference. For the example mentioned in the class header,
	 * the reference value is 50
	 * @return
	 */
	public float getReferenceValue();
	
	/**
	 * set the reference value
	 * @param value
	 */
	public void setReferenceValue(float value);

}
