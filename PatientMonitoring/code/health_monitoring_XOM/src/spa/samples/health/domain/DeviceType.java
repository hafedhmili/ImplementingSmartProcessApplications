package spa.samples.health.domain;

import java.util.Iterator;

/**
 * The type of device. There are some "standard medical device types"
 * that take specific types of measurements. For example, a Blood 
 * Pressure Monitor which would read blood pressure and pulse
 * @author hafedhmili
 *
 */
public interface DeviceType extends SPAType {
	
	/**
	 * returns the name of the DeviceType
	 * @return
	 */
	public String getName();
	
	/**
	 * returns the list of measurements (instances of 
	 * <code>ReadingType</code>) supported by this 
	 * device type.
	 * 
	 * For the blood pressure monitor, we get three
	 * <code>ReadingType</code>s: 1) systolic pressure, 2) diastolic
	 * pressure, and 3) pulse
	 * 
	 * @return
	 */
	public Iterator<ReadingType> getSupportedReadingTypes();
	
	/**
	 * We can "ask" a device type if it support a specific type
	 * of reading.
	 * @param type
	 * @return
	 */
	public boolean supportsReadingType(ReadingType type);
	
	/**
	 * Add <code>type</code> among the reading types supported
	 * by the device type.
	 * @param type
	 */
	public void addReadingType(ReadingType type);
	
	/**
	 * remove <code>type</code> from the list of reading types
	 * supported by the device type.
	 * 
	 * If the argument is currently supported, it is returned. If it
	 * was not supported in the first place, null is returned
	 * @param type
	 * @return
	 */
	public ReadingType removeReadingType(ReadingType type);

}
