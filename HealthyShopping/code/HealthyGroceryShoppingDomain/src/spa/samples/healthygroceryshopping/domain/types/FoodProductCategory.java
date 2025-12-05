/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface FoodProductCategory extends FoodProduct {
	
	public String getFDCId();
	
	public void setFDCId(String fdcId);
	
	public void addSubcategory(FoodProductCategory category);
	
	public FoodProductCategory removeSubcategory(FoodProductCategory category);
	
	public Iterator<FoodProductCategory> getSubcategories();

}
