/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This is the root interface for Food related things: it has two
 * subinterfaces: <code>FoodProductItem</code>, which represents individual products (we
 * can think of it as an 'SKU', i.e. a product catalog entry, that has a price associated with
 * it), and <code>FoodProductCategory</code>, which is any conceptual grouping of 
 * <code>FoodProductItem</code>.
 * 
 * For example, at m,y grocery store, I can buy a TAZO - ZEN - Gree Tea, 20-teabag box, for 4.49$, 
 * a TAZO - ORGANIC CHAI - Black Tea 20-teabag box, for 4.99$, or a a STASH - GREEN TEA - PREMIUM GREEN 
 * 20-teabag, for 4.29 $. Those are all <code>FoodProductItem</code>s. The first two are TAZO 20-teabag 
 * boxes--that is a <code>FoodProductCategory</code>. The first and the third are both GREEN TEA 20-teabag
 * boxes. That is also a <code>FoodProductCategory</code>.
 * 
 * Food products are characterized by:
 * 1)  a unit of measurement. For example, tea boxes are sold by the unit. Bulk apples are sold by weight, 
 * typically pound of kg.
 * 2) nutritional info. A <code>FoodProductItem</code> typically has that information printed on the product
 * package.
 * 
 * Here we make a provision for the case where a food product might have two nutritional infos, produced by
 * two different authorities, possibly: 1) the manufacturer, and 2) some independent authority. See
 * @see <code>FoodProductNutritionalInfo</code>
 * 
 * Author: Hafedh Mili
 */
public interface FoodProduct extends Product {
		
	/**
	 * return the unit of measurement
	 * @return
	 */
	public MeasurementUnit getMeasurementUnit();
	
	/**
	 * modifies the unit of measurement
	 * @param unit
	 */
	public void setMeasurementUnit(MeasurementUnit unit);
	
	/**
	 * add a <code>FoodProductNutritionalInfo</code> to the product
	 * @param fpnInfo
	 */
	public void addNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	/**
	 * remove the <code>FoodProductNutritionalInfo</code> <code>fpnInfo</code>
	 * @param fpnInfo
	 * @return
	 */
	public FoodProductNutritionalInfo removeNutritionalInfo(FoodProductNutritionalInfo fpnInfo);
	
	/**
	 * return the list of nutritional product infos. In most cases, the list will
	 * contain a single element.
	 * @return
	 */
	public Iterator<FoodProductNutritionalInfo> getNutritionalInfo();

}
