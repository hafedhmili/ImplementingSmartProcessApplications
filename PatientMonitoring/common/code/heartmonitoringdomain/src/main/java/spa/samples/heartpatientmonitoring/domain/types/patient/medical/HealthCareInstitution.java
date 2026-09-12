package spa.samples.heartpatientmonitoring.domain.types.patient.medical;

import spa.samples.heartpatientmonitoring.domain.types.util.Address;

public record HealthCareInstitution(String providerCode, String name, Address address) {
	
	public String toString() {
		return name + "[" + providerCode + "], at " + address;
	}

}
