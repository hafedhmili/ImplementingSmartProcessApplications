package spa.samples.heartpatientmonitoring.domain.types;

import java.util.Iterator;

public interface HealthConditionType {
	
	public HealthConditionType getSupertype();
	
	public Iterator<HealthConditionType> getSubtypes();
	
	public void addSubtype(HealthConditionType subtype);
	
	public HealthConditionType removeSubtype(HealthConditionType subtype);
	
	public void setSupertype(HealthConditionType supertype);
	
}
