package spa.samples.heartpatientmonitoring.heartmonitor.types.device;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFormat;

public interface DeviceModel {
	
	public DeviceFamily getDeviceFamily();
	
	public String getModelName();
	
	public String getManufacturer();
	
	public String getModelDescription();
	
	public void setModelDescription(String deviceDescription);
	
	public Iterator<ECGFormat> getECGFormats();
	
	public void addECGFormat(ECGFormat ecgFormat);
	
	public ECGFormat removeECGFormat(ECGFormat ecgFormat);

	public boolean supportsFormat(ECGFormat csv);

}
