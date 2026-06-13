/**
 * 
 */
package spa.samples.heartpatientmonitoring.domain.types.ecg;

import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;

/**
 * Author: Hafedh Mili
 */
public interface ECGraw extends Measurement {
	
	public String getDeviceID();
	
	public void setDeviceID(String deviceID);	
	
	public ECGFile getRawData();
	
	public void setRawData(ECGFile rawDataFile);
	
	public ECGFormat getFormat();

}
