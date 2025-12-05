/**
 * 
 */
package spa.samples.healthygroceryshopping.domain;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface Food {
	
	public String getName();
	
	public String getDescription();
	
	public void setDescription();
	
	public String brandOrVariety();
	
	public void setBrandOrVariety(String brandOrVariety);
	
	public String getProducer();
	
	public void setProducer(String producerId);
	
	public MeasurementUnit getMeasurementUnit();
	
	public void setMeasurementUnit(MeasurementUnit unit);
	
	public void addNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	public FoodProductNutritionalInfo removeNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	public Iterator<FoodProductNutritionalInfo> getNutritionalInfo();

}
