/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This interface represents food categories. @see <code>FoodProduct</code>
 * where we explain the difference between <code>FoodProductItem</code>
 * and <code>FoodProductCategory</code>.
 * 
 * Some, but not all, categories will have FDC Ids.
 * 
 * Product categories constitue a hierarchy. The category of "Tea" has two 
 * non-mutually exclusive subcategories "TAZO teas", and "Green teas".
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface FoodProductCategory extends FoodProduct {
	
	/**
	 * return the FDC Id, if one exists/is known
	 * @return
	 */
	public String getFDCId();
	
	/**
	 * set the FDC Id
	 * @param fdcId
	 */
	public void setFDCId(String fdcId);
	
	/**
	 * Add subcategory <code>category</code>
	 * 
	 * @param category
	 */
	public void addSubcategory(FoodProductCategory category);
	
	/**
	 * remove subcategory <code>category</code>
	 * 
	 * @param category
	 */
	public FoodProductCategory removeSubcategory(FoodProductCategory category);
	
	/**
	 * return the list of subcategories
	 * @return
	 */
	public Iterator<FoodProductCategory> getSubcategories();

}
