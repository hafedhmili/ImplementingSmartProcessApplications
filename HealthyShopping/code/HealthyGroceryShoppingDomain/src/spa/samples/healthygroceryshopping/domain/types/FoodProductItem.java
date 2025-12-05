/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface FoodProductItem extends FoodProduct {
	
	public float getPricePerUnit();
	
	public void setPrincePerUnit(float price);
	
	public void addProductCategory(FoodProductCategory category);
	
	public FoodProductCategory removeProductCategory(FoodProductCategory category);
	
	public Iterator<FoodProductCategory> getProductCategories();

}
