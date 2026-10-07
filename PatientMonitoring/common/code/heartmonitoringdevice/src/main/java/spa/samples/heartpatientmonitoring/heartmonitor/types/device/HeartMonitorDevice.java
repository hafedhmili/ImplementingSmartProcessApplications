package spa.samples.heartpatientmonitoring.heartmonitor.types.device;

import java.time.Duration;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.device.MeasurementDevice;

public interface HeartMonitorDevice extends MeasurementDevice {

	
	/**
	 * take an ECG with the specified format and duration. This is the 'command' to start taking an ECG. 
	 * 
	 * This is the same as <code>takeMeasurement(Duration duration)</code>, but it is more specific to ECGs.
	 * 
	 * @param duration
	 * @return
	 */
	public ECGraw takeECG(Duration duration);

	/**
	 * returns the current recording format for the device. 
	 * @return
	 */
	public ECGFormat getCurrentECGFormat();

	/**
	 * We assume that some device models support several recording formats, and a 
	 * given device can have the recording format set among the supported formats
	 * @param format
	 */
	public void setCurrentECGFormat(ECGFormat format) throws UnsupportedECGFormat;

	/**
	 * This method checks whether this heart monitor supports the ECGFormat <code>ecgFormat</code>.
	 * It does so by checking with the device model.
	 * @param ecgFormat
	 * @return
	 */
	public boolean supportsECGFormat(ECGFormat ecgFormat);
}
