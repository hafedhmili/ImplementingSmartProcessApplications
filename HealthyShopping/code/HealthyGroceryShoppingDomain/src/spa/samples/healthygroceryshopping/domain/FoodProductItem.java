/**
 * 
 */
package spa.samples.healthygroceryshopping.domain;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface FoodProductItem extends Food {
	
	public float getPricePerUnit();
	
	public void setPrincePerUnit(float price);
	
	public void addProductCategory(FoodProductCategory category);
	
	public FoodProductCategory removeProductCategory(FoodProductCategory category);
	
	public Iterator<FoodProductCategory> getProductCategories();

}
