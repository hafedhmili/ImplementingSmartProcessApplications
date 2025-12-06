/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * This interface represents the nutritional parameter
 * score for a cart. For example, if I am hosting a party, and
 * I bought a lot of soda and chips, I will have a score of TooHigh for sugar
 * , salt, and trans fat. Each one of those will be represented by an
 * instance of this interface
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface NutritionalParameterScore {
	
	/**
	 * the corresponding nutritional parameter (e.g. "sodium content"
	 * or "trans fat" content.
	 * @return
	 */
	public NutritionalParameter getNutritionalParameter();
	
	/**
	 * get the actual score value, which could be High, Moderate, erc
	 * @return
	 */
	public ScoreValue getScoreValue();
	
	/**
	 * set the  score value to <code>score</code>
	 * @param score
	 */
	public void setScoreValue(ScoreValue score);

}
