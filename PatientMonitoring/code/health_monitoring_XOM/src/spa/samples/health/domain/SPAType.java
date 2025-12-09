/**
 * 
 */
package spa.samples.health.domain;

import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface SPAType {
	
	/**
	 * returns the name of the act
	 * @return
	 */
	public String getName();
	
	/**
	 * returns the description of the act type
	 * @return
	 */
	public String getDescription();
	
	/**
	 * returns the supertype, if one is set
	 * @return
	 */
	public SPAType getSupertype();
	
	/**
	 * return the subtypes of the receiver, if some had
	 * been defined
	 * @return
	 */
	public Iterator<SPAType> getSubtypes();
	
	/**
	 * add <code>subtype</code> as one of the subtypes
	 * of the receiver. This will take care to connect in
	 * both directions
	 * @param subtype
	 */
	public void addSubtype(SPAType subtype);
	
	/**
	 * will remove <code>subtype</code> from the list of
	 * subtypes of the receiver. Will disconnect
	 * @param subtype
	 * @return
	 */
	public SPAType removeSubtype(SPAType subtype);
	
	/**
	 * defines <code>supertype</code> as a supertype
	 * of the receiver. Will make sure that the 
	 * connexion is established in both directions
	 * @param supertype
	 */
	public void setSupertype(SPAType supertype);
	
	/**
	 * checks whether the receiver inherits from <code>ancestor</code>.
	 * @param ancestor
	 * @return
	 */
	public boolean inheritsFrom(SPAType ancestor);

}
