package spa.samples.health.domain;

public interface Address {
	
	public static enum AddressType {Personal, Professional};
	
	public static enum StreetType {Street, Avenue, Boulevard, Alley};
	
	public static enum StreetOrientation {East, West, North, South}
	
	public String getDoorNumber();
	
	public String getStreetNumber();
	
	public String getStreetName();
	
	public StreetType getStreetType();
	
	public StreetOrientation getStreetOrientation();
	
	public AddressType getAddressType();
	
	public String getCity();
	
	public String getStateProvince();
	
	public String getPostalCode();
	
	public Country getCountry();
	
}
