/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface Sensor {
	
	/**
	 * return the sensor model
	 * @return
	 */
	public SensorModel getSensorModel();
	
	/**
	 * return the sensor state
	 * @return
	 */
	public SensorState getState();
	
	/**
	 * set the sensor state
	 * @param state
	 */
	public void setState(SensorState state);

}
