package spa.samples.health.domain;

public interface ReadingValue {
	
	/**
	 * The value can a number or an interval, or whatever
	 * @return
	 */
	public Object getValue();
	
	/**
	 * the unit for the value. 
	 * @return
	 */
	public ValueUnit getUnit();

}
