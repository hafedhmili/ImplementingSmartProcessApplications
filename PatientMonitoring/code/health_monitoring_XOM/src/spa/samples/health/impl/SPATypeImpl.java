/**
 * 
 */
package spa.samples.health.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import spa.samples.health.domain.SPAType;

/**
 * Author: Hafedh Mili
 */
public class SPATypeImpl implements SPAType {
	
	private String name;
	
	private String description;
	
	private SPAType superType;
	
	private Collection<SPAType> subTypes;
	
	public SPATypeImpl(SPAType supType, String name, String description) {
		this(name,description);
		if (supType != null) supType.addSubtype(this);
	}
	
	public SPATypeImpl(String name, String description) {
		this.name = name;
		this.description = description;
		this.subTypes = new ArrayList<SPAType>();
	}

	@Override
	public SPAType getSupertype() {
		return superType;
	}

	@Override
	public Iterator<SPAType> getSubtypes() {
		return subTypes.iterator();
	}

	@Override
	public void addSubtype(SPAType subtype) {
		subTypes.add(subtype);
		((SPATypeImpl)subtype).assignSuperType(this); 
	}

	@Override
	public SPAType removeSubtype(SPAType subtype) {
		if (subTypes.contains(subtype)) {
			subTypes.remove(subtype);
			((SPATypeImpl)subtype).assignSuperType(null);
			return subtype;
		}
		return null;
	}

	@Override
	public void setSupertype(SPAType supertype) {
		supertype.addSubtype(this);
	}
	
	private void assignSuperType(SPAType superType) {
		this.superType = superType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean inheritsFrom(SPAType ancestor) {
		if (this == ancestor) return true;
		if (superType == null) return false;
		return this.superType.inheritsFrom(ancestor);
	}

}
