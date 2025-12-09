/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface SensorModel {
	
	/**
	 * return the model number
	 * @return
	 */
	public String getModelNumber();
	
	/**
	 * return the model name
	 * @return
	 */
	public String getModelName();
	
	/**
	 * return the type of sensor
	 * @return
	 */
	public SensorType getSensorType();
	
	/**
	 * return the manufacturer
	 * @return
	 */
	public String getManufacturer();
	
	/**
	 * set the manufacturer
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer);
	
	/**
	 * return the description
	 * @return
	 */
	public String getDescription();
	
	/**
	 * set the description
	 * @param description
	 */
	public void setDescription(String description);
	
	

}
