/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface NutritionalIndication {
	
	public NutritionalParameter getParameter();
	
	public MeasurementUnit getUnit();
	
	public void setUnit (MeasurementUnit unit);
	
	public Range getValue();
	
	public void setValue(Range range);
	
	public MeasurementUnit getReferenceUnit();
	
	public void setReferenceUnit(MeasurementUnit referenceUnit);
	
	public Range getReferenceValue();
	
	public void setReferenceValue(Range referenceValue);
	
	public MeasurementUnit getPeriodUnit();
	
	public void setPeriodUnit(MeasurementUnit periodUnit);
	
	public Range getPeriodValue();
	
	public void setPeriodValue(Range periodValue);

}
