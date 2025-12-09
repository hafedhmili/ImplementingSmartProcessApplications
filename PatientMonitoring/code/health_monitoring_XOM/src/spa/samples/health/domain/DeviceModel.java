package spa.samples.health.domain;

public interface DeviceModel {
	
	/**
	 * the type of device. This determines the kinds of 
	 * measurements (<code>ReadingType</code>) that it can make
	 * @return
	 */
	public DeviceType getDeviceType();
	
	/**
	 * The manufacturer's name, as a string
	 * @return
	 */
	public String getManufacturer();
	
	/**
	 * The model's name
	 * @return
	 */
	public String getModelName();
	
	/**
	 * The model's description
	 * @return
	 */
	public String getModelDescription();

}
