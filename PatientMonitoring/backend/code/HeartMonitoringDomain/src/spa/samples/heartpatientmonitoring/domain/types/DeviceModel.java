package spa.samples.heartpatientmonitoring.domain.types;

import java.util.Iterator;

public interface DeviceModel {
	
	public DeviceFamily getDeviceFamily();
	
	public String getModelName();
	
	public String getManufacturer();
	
	public String getModelDescription();
	
	public void setModelDescription(String deviceDescription);
	
	public Iterator<ECGFormat> getECGFormats();
	
	public void addECGFormat(ECGFormat ecgFormat);
	
	public ECGFormat removeECGFormat(ECGFormat ecgFormat);

}
