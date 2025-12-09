/**
 * 
 */
package spa.samples.health.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import spa.samples.health.domain.DeviceType;
import spa.samples.health.domain.ReadingType;

/**
 * Author: Hafedh Mili
 */
public class DeviceTypeImpl extends SPATypeImpl implements DeviceType {
	
	private Collection<ReadingType> readingTypes;

	/**
	 * @param supType
	 * @param name
	 * @param description
	 */
	public DeviceTypeImpl(DeviceType supType, String name, String description) {
		super(supType, name, description);
		readingTypes = new ArrayList<ReadingType>();
	}
	
	public DeviceTypeImpl(String name, String description) {
		this(null, name, description);
	}

	@Override
	public Iterator<ReadingType> getSupportedReadingTypes() {
		return readingTypes.iterator();
	}

	@Override
	public boolean supportsReadingType(ReadingType type) {
		return readingTypes.contains(type);
	}

	@Override
	public void addReadingType(ReadingType type) {
		if (!supportsReadingType(type)) readingTypes.add(type);
	}

	@Override
	public ReadingType removeReadingType(ReadingType type) {
		if (readingTypes.remove(type)) return type;
		return null;
	}

}
