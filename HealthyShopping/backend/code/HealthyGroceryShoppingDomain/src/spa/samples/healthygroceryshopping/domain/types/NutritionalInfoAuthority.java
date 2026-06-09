/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * 
 * This is used to represent the organisation that produces the nutritional info.
 * 
 * It is typically, the company that manufactures the product (hence 
 * <code>NutritionalParameterValue</code> is Private), or the FDC or some regulatory
 * agency.
 * 
 * Its type (<code>authorityType</code>) can be public or private
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface NutritionalInfoAuthority {
	
	/**
	 * name of the authority
	 * @return
	 */
	public String getName();
	
	/**
	 * its description
	 * @return
	 */
	public String getDescription();
	
	/**
	 * return its type (public or private)
	 * @return
	 */
	public AuthorityType getAuthorityType();
	
	/**
	 * set its type (Public or Private)
	 * @param authType
	 */
	public void setAuthorityType(AuthorityType authType);

}
