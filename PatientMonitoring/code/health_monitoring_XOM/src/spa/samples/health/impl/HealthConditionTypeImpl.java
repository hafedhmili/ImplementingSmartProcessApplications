/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.HealthConditionType;
import spa.samples.health.domain.SPAType;

/**
 * Author: Hafedh Mili
 */
public class HealthConditionTypeImpl extends SPATypeImpl implements HealthConditionType {

	/**
	 * @param supType
	 * @param name
	 * @param description
	 */
	public HealthConditionTypeImpl(SPAType supType, String name, String description) {
		super(supType, name, description);
	}

	public HealthConditionTypeImpl(String name, String description) {
		super(name, description);
	}


}
