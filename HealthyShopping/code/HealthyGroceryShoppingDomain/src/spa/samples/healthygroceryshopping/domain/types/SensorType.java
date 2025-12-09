/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

import java.util.Iterator;

/**
 * 
 * This class represent sensor types, of which we can have several (sensor) models.
 * 
 * For example, we can sensors that read tags, and then, have subtypes corresponding to
 * different kinds of tags (e.g. RFID tags), and then have different models of readers of
 * passive RFID tags
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface SensorType {
	
	/**
	 * type name
	 * @return
	 */
	public String getName ();
	
	/**
	 * type description
	 * @return
	 */
	public String getDescription();
	
	/**
	 * list of subtypes
	 * @return
	 */
	public Iterator<SensorType> getSubtypes();
	
	/**
	 * add a subtype
	 * @param type
	 */
	public void addSubtype(SensorType type);
	
	/**
	 * remove a subtype
	 * @param type
	 * @return
	 */
	public SensorType removeSubtype(SensorType type);
	
}
