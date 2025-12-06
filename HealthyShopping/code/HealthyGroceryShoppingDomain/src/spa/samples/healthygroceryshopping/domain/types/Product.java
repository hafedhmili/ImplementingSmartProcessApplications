/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * 
 * This interface represents the parent class of the products that we can find
 * at a grocery store, and put in a cart. It can be food product (subinterface),
 * or a coffee machine, or a peeler, or a salt shaker, or whichever product
 * one can find at grocery chains.
 * 
 * All products have names, descriptions, brands (for manufactured products) or varieties
 * (for fruits, vegetables or meats), and a producer which can be a nmanufacturer (e.g.
 * General Mills) or an agricultural enterprise/farmm
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public interface Product {
	
	/**
	 * the name of the product
	 * @return
	 */
	public String getName();
	
	/**
	 * the description of the product
	 * @return
	 */
	public String getDescription();
	
	/**
	 * modifies the description of the product
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * returns the brand or variety of the product. In reality,
	 * we can have a hierarchy of brands. For the purposes of this
	 * case study, we will keep things simple
	 * @return
	 */
	public String brandOrVariety();
	
	/**
	 * modifies the brand or variety of the product
	 * @param brandOrVariety
	 */
	public void setBrandOrVariety(String brandOrVariety);
	
	/**
	 * returns the producer (a manufacturer or a farming enterprise)
	 * @return
	 */
	public String getProducer();
	
	/**
	 * modifies/assigns the producer. The producer is identified by a string,
	 * which could be a name, or an ID in some sort of register (could be a corporation
	 * number, a tax identifier, whatever ...)
	 * @param producerId
	 */
	public void setProducer(String producerId);


}
