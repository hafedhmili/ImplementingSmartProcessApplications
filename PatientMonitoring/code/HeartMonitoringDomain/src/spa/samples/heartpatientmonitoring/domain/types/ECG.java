package spa.samples.heartpatientmonitoring.domain.types;


public interface ECG extends ClinicalTest{
	
	
	public ECGFile getRawData();
	
	public void setRawData(ECGFile rawDataFile);
	
	public ECGAnalysisReport getAnalysisReport();
	
	public void setAnalysisReport(ECGAnalysisReport analysisReport);
	
	public ECGProcessingState getProcessingState();
	
	public void setProcessingState(ECGProcessingState processingState);
		
	public HeartMonitorDevice getDevice();
	
	public ECGFormat getFormat();

}
