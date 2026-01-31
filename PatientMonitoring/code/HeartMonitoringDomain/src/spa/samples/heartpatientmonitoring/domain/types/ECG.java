package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;

public interface ECG {
	
	public Patient getPatient();
	
	public Instant getStartTime();
	
	public Instant getEndTime();
	
	public ECGFile getRawData();
	
	public void setRawData(ECGFile rawDataFile);
	
	public ECGAnalysisReport getAnalysisReport();
	
	public void setAnalysisReport(ECGAnalysisReport analysisReport);
	
	public ECGProcessingState getProcessingState();
	
	public void setProcessingState(ECGProcessingState processingState);
	
	public Location getStartLocation();
	
	public void setStartLocation(Location startLocation);
	
	public Location getEndLocation();
	
	public void setEndLocation(Location endLocation);
	
	public HeartMonitorDevice getDevice();
	
	public ECGFormat getFormat();

}
