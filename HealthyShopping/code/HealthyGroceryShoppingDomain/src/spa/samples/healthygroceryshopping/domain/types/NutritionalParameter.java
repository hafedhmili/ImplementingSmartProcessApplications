/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This interface is used to represent the nutritional parameters/elements for
 * foods. Example "nutritional parameters" include sodium contents, Carbohydrates,
 * Polyunsatured fats, Proteins, etc.
 * 
 * Each nutritional paramater has a <code>name</code>, a <code>description</code>,
 * <code>valueUnits</code>, which are the possible measurement units for this 
 * parameter/element; e.g. Potassium is typically measured in milligrams, and 
 * <code>referenceUnits</code>, which are the possible measurement units for 
 * the reference used for the parameter. For example, when we measure the 
 * sodium content of a particular food item, the reference unit could be grams, as in
 * "30 mg of Potassium <i>per</i> 30 <b>grams</b> of [food item]", where <b>gram</b> is one
 * of the reference units. For liquid products, we are going to have volume units (liquid ounce,
 * liter, quart, etc.)
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface NutritionalParameter {
	
	/**
	 * the name of the nutritional parameter
	 * @return
	 */
	public String getName();
	
	/**
	 * the description of the nutritional parameter
	 * @return
	 */
	public String getDescription();
	
	/**
	 * change the description of the parameter
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * return an iterator over the same of possible measurement units for
	 * this parameter. For example, "Carbohydrates" are usually measured
	 * in grams, whereas Potassium and Calcium are typically measured in 
	 * milligrams (mg)
	 * @return
	 */
	public Iterator<MeasurementUnit> getValueUnits();
	
	/**
	 * add a measurement unit to the list of measurement units
	 * @param unit
	 */
	public void addValueUnit(MeasurementUnit unit);
	
	/**
	 * removes the MeasurementUnit <code>unit</code> from the list of 
	 * acceptable measurement units for this nutritional parameter
	 * @param unit
	 * @return
	 */
	public MeasurementUnit removeValueUnit(MeasurementUnit unit);
	
	/**
	 * return an iterator over the same of possible reference units, i.e.
	 * the measurement units used for the reference quality; it is the "xxx" in 
	 * "per 30 xxx of ". For solid foods, it is usually a weight (grams, kilograms).
	 * For liquids, it is usually a volume unit
	 * 
	 * @return
	 */
	public Iterator<MeasurementUnit> getReferenceUnits();
	
	/**
	 * add a measurement unit to the list of reference units for 
	 * the reference
	 * @param unit
	 */
	public void addRefertenceUnit(MeasurementUnit unit);
	
	/**
	 * removes the MeasurementUnit <code>unit</code> from the list of 
	 * acceptable reference units for this nutritional parameter
	 * @param unit
	 * @return
	 */
	public MeasurementUnit removeReferenceUnit(MeasurementUnit unit);
		
	

}
