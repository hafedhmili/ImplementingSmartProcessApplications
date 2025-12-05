/**
 * 
 */
package spa.samples.healthygroceryshopping.domain;

import java.util.Iterator;

/**
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface FoodProductCategory extends Food {
	
	public String getFDCId();
	
	public void setFDCId(String fdcId);
	
	public void addSubcategory(FoodProductCategory category);
	
	public FoodProductCategory removeSubcategory(FoodProductCategory category);
	
	public Iterator<FoodProductCategory> getSubcategories();

}
