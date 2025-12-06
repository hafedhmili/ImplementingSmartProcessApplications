/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * 
 * We can think of this interface as representation the nutritional info
 * sheet of a food product. It is represented by:
 * 1) the food product it concerns. This can be a single product
 * (TAZO - ZEN - GREEN TEA, 20-teabag box), or a class/category
 * of products ("TAZO teas", or "Green teas")
 * 2) the date at which the info was produced, if known. If the info
 * is produced by a public or regulatory authority, usually, we will have/know
 * the date
 * 3) the authority that produced the info. It can be the manufacturer, 
 * or the US FDC (typically for product catregories).
 * 4) the list of nutritional parameter values, such as calories,
 * carbohydrates, proteins, fats, sodium, potassium, etc.
 * 
 * Author: Hafedh Mili
 */
public interface FoodProductNutritionalInfo {
	
	/**
	 * return the food product concerned by this nutritional info
	 * @return
	 */
	public FoodProduct getFood();
	
	/**
	 * the date at which this info was produced/published, if known
	 * @return
	 */
	public Instant getInfoDate();
	
	/**
	 * return the authority that produced the info
	 * @return
	 */
	public NutritionalInfoAuthority getNutritionalInfoAuthority();
	
	/**
	 * set the authority that produced this info
	 * @param authority
	 */
	public void setNutritionalInfoAuthority(NutritionalInfoAuthority authority);
	
	/**
	 * return the list of nutritional parameter value (e.g., the sodium content per tea bag)
	 * @return
	 */
	public Iterator<NutritionalParameterValue> getNutritionalParameterValues();
	
	/**
	 * add the nutritional <code>npValue</code> parameter value 
	 * (e.g., the sodium content per tea bag)
	 * @param npValue
	 */
	public void addNutritionalParameterValue(NutritionalParameterValue npValue);
	
	/**
	 * remove the nutritional <code>npValue</code> parameter value 
	 * (e.g., the sodium content per tea bag)
	 * @param npValue
	 */
	public NutritionalParameterValue removeNutritionalParameterValue(NutritionalParameterValue npValue);
	
}
