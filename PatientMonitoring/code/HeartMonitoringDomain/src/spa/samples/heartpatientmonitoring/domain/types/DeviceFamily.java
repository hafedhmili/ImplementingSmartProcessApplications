package spa.samples.heartpatientmonitoring.domain.types;

import java.util.Iterator;

public interface DeviceFamily {
	
	public DeviceType getDeviceType();
	
	public DeviceFamily getSuperFamily();
	
	public String getFamilyName();
	
	public String getFamilyDescription();
	
	public void setFamilyDescription(String familyDescription);
	
	public Iterator<DeviceFamily> getSubfamilies();
	
	public void addSubfamily(DeviceFamily subFamily);
	
	public DeviceFamily removeDeviceSubfamily(DeviceFamily deviceFamily);

}
