package spa.samples.health.domain;

public enum Country {
	CA ("Canada"),
	FR("France"),
	IT("Italy"),
    TN ("Tunisia"),
    USA ("United States of America");

    private final String fullName;

    private Country(final String value) {
        this.fullName = value;
    }

    public String getFullName() { return fullName; }
}