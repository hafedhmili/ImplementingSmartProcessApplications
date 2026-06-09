package spa.samples.heartpatientmonitoring.domain.types;

public record HealthCareInstitution(String providerCode, String name, Address address) {
	
	public String toString() {
		return name + "[" + providerCode + "], at " + address;
	}

}
