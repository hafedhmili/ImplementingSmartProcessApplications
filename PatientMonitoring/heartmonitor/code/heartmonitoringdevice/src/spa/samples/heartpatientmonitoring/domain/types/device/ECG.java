package spa.samples.heartpatientmonitoring.domain.types.device;

import spa.samples.heartpatientmonitoring.domain.types.ClinicalTestResult;
import spa.samples.heartpatientmonitoring.domain.types.ECGAnalysisReport;

public interface ECG extends ClinicalTestResult{
	
	
	public ECGFile getRawData();
	
	public void setRawData(ECGFile rawDataFile);
	
	public ECGAnalysisReport getAnalysisReport();
	
	public void setAnalysisReport(ECGAnalysisReport analysisReport);
	
	public ECGProcessingState getProcessingState();
	
	public void setProcessingState(ECGProcessingState processingState);
		
	public HeartMonitorDevice getDevice();
	
	public ECGFormat getFormat();

}
