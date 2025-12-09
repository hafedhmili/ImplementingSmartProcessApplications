/**
 * 
 */
package spa.samples.health.impl;


import spa.samples.health.domain.HealthEpisodeType;


/**
 * Author: Hafedh Mili
 */
public class HealthEpisodeTypeImpl extends SPATypeImpl implements HealthEpisodeType {

	/**
	 * @param name
	 * @param description
	 */
	public HealthEpisodeTypeImpl(String name, String description) {
		super(name, description);
	}
	
	/**
	 * @param name
	 * @param description
	 */
	public HealthEpisodeTypeImpl(HealthEpisodeType supType, String name, String description) {
		super(supType,name,description);
	}

}
