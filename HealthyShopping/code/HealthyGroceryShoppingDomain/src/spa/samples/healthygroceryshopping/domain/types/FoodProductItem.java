/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This is the intyerface that represents an actual product that shows up on the 
 * grocery bill. It has a <code>pricePerUnit</code>, and it belongs to a bunch
 * of product categories. For example, the TAZO - ZEN - GREEN TEA, 20-teabag 
 * box belongs to the <code>FoodProductCategory</code> "TAZO ZEN series teas",
 * "TAZO teas", and "Green Teas", all manufactures/brands included.
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface FoodProductItem extends FoodProduct {
	
	/**
	 * return the price per unit
	 * @return
	 */
	public float getPricePerUnit();
	
	/**
	 * set the price per unit
	 * @param price
	 */
	public void setPricePerUnit(float price);
	
	/**
	 * add <code>category</code> to the list of <code>FoodProductCategory</code>'ies
	 * of this product
	 * @param category
	 */
	public void addProductCategory(FoodProductCategory category);
	
	/**
	 * remove <code>category</code> from the list of <code>FoodProductCategory</code>'ies
	 * of this product
	 * @param category
	 * @return
	 */
	public FoodProductCategory removeProductCategory(FoodProductCategory category);
	
	/**
	 * return the list of <code>FoodProductCategory</code>'ies of this product
	 * @return
	 */
	public Iterator<FoodProductCategory> getProductCategories();

}
