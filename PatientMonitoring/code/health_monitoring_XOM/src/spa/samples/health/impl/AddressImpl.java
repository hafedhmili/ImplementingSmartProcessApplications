/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.Address;
import spa.samples.health.domain.Country;

/**
 * Author: Hafedh Mili
 */
public class AddressImpl implements Address {
	
	private String doorNumber;
	
	private String streetNumber;
	
	private String streetName;
	
	private StreetType streetType;
	
	private StreetOrientation streetOrientation;
	
	private AddressType addressType;
	
	private String city;
	
	private String postalCode;
	
	private String stateProvince;
	
	private Country country;

	@Override
	public String getDoorNumber() {
		return doorNumber;
	}

	@Override
	public String getStreetNumber() {
		return streetNumber;
	}

	@Override
	public String getStreetName() {
		return streetName;
	}

	@Override
	public StreetType getStreetType() {
		// TODO Auto-generated method stub
		return streetType;
	}

	@Override
	public StreetOrientation getStreetOrientation() {
		return streetOrientation;
	}

	@Override
	public AddressType getAddressType() {
		return addressType;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public String getStateProvince() {
		return stateProvince;
	}

	@Override
	public String getPostalCode() {
		return postalCode;
	}

	@Override
	public Country getCountry() {
		return country;
	}

}
