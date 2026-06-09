/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface HealthParameter {
	
	/**
	 * return the name of the health parameter
	 * @return
	 */
	public String getName();
	
	/**
	 * return the description of the health parameter
	 * @return
	 */
	public String getDescription();
	
	/**
	 * set the description of the health parameter
	 * @param description
	 */
	public void setDescription(String description);

}