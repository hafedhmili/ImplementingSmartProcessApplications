/**
 * 
 */
package spa.samples.healthygroceryshopping.domain;

/**
 * Author: Hafedh Mili
 */
public interface NutritionalInfoAuthority {
	
	public String getName();
	
	public String getDescription();
	
	public AuthorityType getAuthorityType();
	
	public void setAuthorityType(AuthorityType authType);

}
