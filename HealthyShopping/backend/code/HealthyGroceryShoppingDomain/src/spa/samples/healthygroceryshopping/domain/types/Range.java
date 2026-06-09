/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Record used to represent ranges. We can use it to 
 * represent a single value
 * Author: Hafedh Mili
 */
public record Range(float LowerBound , boolean lowerIncluded, float UpperBound, boolean upperIncluded ) {
	
	public Range(float singleValue) {
		this(singleValue,true,singleValue,true);
	}

}
