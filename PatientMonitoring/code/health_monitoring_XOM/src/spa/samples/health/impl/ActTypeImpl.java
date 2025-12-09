/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.ActType;

/**
 * Author: Hafedh Mili
 */
public class ActTypeImpl extends SPATypeImpl implements ActType {

	/**
	 * @param supType
	 * @param name
	 * @param description
	 */
	public ActTypeImpl(ActType supType, String name, String description) {
		super(supType, name, description);
	}
	
	public ActTypeImpl(String name, String description) {
		super(name, description);
	}

}
