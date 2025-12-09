/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 * 
 * This interface represents the nutritional indications that correspond to various
 * health condition types.
 * 
 * For example, for high-blood pressure, an indication is a diet low in salt/sodium. 
 * For diabetes or insulin intolerance, it would be low carbohydrate content, etc.
 * 
 * More precisely, for each <code>NutritionalParameter</code> (e.g. sodium content),
 * we specify recommended value ranges of (get/setValue()) expressed in get/setUnits,
 * potentially per X range (reference value) of Y quantity (reference unit), per 
 * Z period (periodValue and periodUnit). 
 * 
 * For example, I could say "for high blood pressure, person should not ingest more
 * that two grams of salt, per 80 kgs of body weight, per day.
 */
public interface NutritionalIndication {
	
	/**
	 * the health condition type in question
	 * @return
	 */
	public HealthConditionType getHealthConditionType();
	
	/**
	 * the nutritional parameter in question
	 * @return
	 */
	public NutritionalParameter getParameter();
	
	/**
	 * the unit of measurement for the nutritional parameter
	 * @return
	 */
	public MeasurementUnit getUnit();
	
	/**
	 * sets the unit of measurement of the nutritional parameter
	 * @param unit
	 */
	public void setUnit (MeasurementUnit unit);
	
	/**
	 * the value range for the nutritional parameter
	 * @return
	 */
	public Range getValue();
	
	/**
	 * sets the value range for the nutritional parameter
	 * @param range
	 */
	public void setValue(Range range);
	
	/**
	 * returns the reference unit. Kgs, in our
	 * example above
	 * 
	 * @return
	 */
	public MeasurementUnit getReferenceUnit();
	
	/**
	 * sets the reference unit
	 * 
	 * @param referenceUnit
	 */
	public void setReferenceUnit(MeasurementUnit referenceUnit);
	
	/**
	 * returns the reference value range (80 in our example above)
	 */
	public Range getReferenceValue();
	
	/**
	 * sets the reference value
	 * 
	 * @param referenceValue
	 */
	public void setReferenceValue(Range referenceValue);
	
	/**
	 * returns the period unit. "day" in our example above
	 * @return
	 */
	public MeasurementUnit getPeriodUnit();
	
	/**
	 * sets the period unit
	 * 
	 * @param periodUnit
	 */
	public void setPeriodUnit(MeasurementUnit periodUnit);
	
	/**
	 * returns the period value. "1" (day) in the example above
	 * @return
	 */
	public Range getPeriodValue();
	
	/**
	 * sets the period value
	 * @param periodValue
	 */
	public void setPeriodValue(Range periodValue);

}
