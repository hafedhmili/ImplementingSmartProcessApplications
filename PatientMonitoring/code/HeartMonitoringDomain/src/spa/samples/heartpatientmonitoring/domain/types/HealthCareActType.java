package spa.samples.heartpatientmonitoring.domain.types;

import java.util.Iterator;

public interface HealthCareActType {

	/**
	 * return the name of the health care act type.
	 * @return
	 */
	public String getName();
	
	/**
	 * return the description of the health care act type
	 * 
	 * @return
	 */
	public String getDescription();
	
	/**
	 * modifies the <code>description</code> of the health care act type
	 * 
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * returns the supertype.
	 * 
	 * @return
	 */
	public HealthCareActType getSupertype();
	
	/**
	 * set/changes the <code>superType</code> of the
	 * receiver.
	 * 
	 * Will make sure that the connection is made in both directions
	 * 
	 * @param superType
	 */
	public void setSupertype(HealthCareActType superType);
	
	/**
	 * returns the list of subtypes of the receiver
	 * @return
	 */
	public Iterator<HealthCareActType> getSubtypes();
		
	/**
	 * adds <code>subType</code> as a child of the
	 * receiver.
	 * 
	 * Will make sure that the connection is made in both directions
	 * 
	 * @param subType
	 */
	public void addSubtype(HealthCareActType subType);
	
	/**
	 * removes <code>subType</code> from the list of children. It returns
	 * the argument to confirm that <code>subType</code> was indeed a 
	 * subtype and null otherwise
	 * 
	 * Will make sure that the connection is removed in both directions.
	 * 
	 * @param subType
	 * @return
	 */
	public HealthCareActType removeSubtype(HealthCareActType subType);
	
	/**
	 * Checks if the receiver is a descendant of the <code>superType</code>,
	 * directly, or transitively.
	 * 
	 * @param superType
	 * @return
	 */
	public boolean isSubtypeOf(HealthCareActType superType);
}

