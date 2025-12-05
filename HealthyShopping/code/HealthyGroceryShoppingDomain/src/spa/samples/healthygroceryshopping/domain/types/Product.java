/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface Product {
	
	public String getName();
	
	public String getDescription();
	
	public void setDescription();
	
	public String brandOrVariety();
	
	public void setBrandOrVariety(String brandOrVariety);
	
	public String getProducer();
	
	public void setProducer(String producerId);


}
