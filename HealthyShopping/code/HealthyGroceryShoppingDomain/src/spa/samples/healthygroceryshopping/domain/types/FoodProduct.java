/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface FoodProduct extends Product {
		
	public MeasurementUnit getMeasurementUnit();
	
	public void setMeasurementUnit(MeasurementUnit unit);
	
	public void addNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	public FoodProductNutritionalInfo removeNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	public Iterator<FoodProductNutritionalInfo> getNutritionalInfo();

}
