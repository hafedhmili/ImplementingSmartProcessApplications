/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface Sensor {
	
	public SensorModel getSensorModel();
	
	public SensorState getState();
	
	public void setState(SensorState state);

}
