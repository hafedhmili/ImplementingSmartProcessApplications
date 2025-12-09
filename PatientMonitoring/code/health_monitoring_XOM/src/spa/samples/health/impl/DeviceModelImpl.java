/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.DeviceModel;
import spa.samples.health.domain.DeviceType;

/**
 * Author: Hafedh Mili
 */
public class DeviceModelImpl implements DeviceModel {
	
	private String modelName;
	
	private String modelDescription;
	
	private String manufacturer;
	
	private DeviceType deviceType;
	
	public DeviceModelImpl(DeviceType type, String manufacturer, String name, String description) {
		this.deviceType = type;
		this.manufacturer = manufacturer;
		this.modelName = name;
		this.modelDescription = description;
	}

	@Override
	public DeviceType getDeviceType() {
		return deviceType;
	}

	@Override
	public String getManufacturer() {
		return manufacturer;
	}

	@Override
	public String getModelName() {
		return modelName;
	}

	@Override
	public String getModelDescription() {
		return modelDescription;
	}

}
