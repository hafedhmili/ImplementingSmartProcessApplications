package spa.samples.heartpatientmonitoring.domain.types;

public record Interval(float lowerBound, boolean lowerBoundIncluded, float upperBound, boolean upperBoundIncluded) {
	
	public String toString() {
		StringBuilder builder = new StringBuilder(""+lowerBound + ".."+ upperBound);
		builder.insert(0, lowerBoundIncluded? "[": "]");
		builder.append(upperBoundIncluded?"]": "[");
		return builder.toString();
	}

}
