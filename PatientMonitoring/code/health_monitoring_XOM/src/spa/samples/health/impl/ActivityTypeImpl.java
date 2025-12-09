/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.ActivityType;

/**
 * Author: Hafedh Mili
 */
public class ActivityTypeImpl extends SPATypeImpl implements ActivityType {
	
	
	public ActivityTypeImpl(ActivityType superType, String name, String description) {
		super(superType,name,description);
	}

	public ActivityTypeImpl(String name, String description) {
		super(name,description);
	}

}
