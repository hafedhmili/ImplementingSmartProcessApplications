/**
 * 
 */
package spa.samples.health.impl;

import spa.samples.health.domain.ReadingValue;
import spa.samples.health.domain.ValueUnit;

/**
 * Author: Hafedh Mili
 */
public class ReadingValueImpl implements ReadingValue {
	
	private Object value;
	
	private ValueUnit valueUnit;
	
	public ReadingValueImpl(Object value, ValueUnit valueUnit) {
		this.value = value;
		this.valueUnit = valueUnit;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public ValueUnit getUnit() {
		return valueUnit;
	}

}
