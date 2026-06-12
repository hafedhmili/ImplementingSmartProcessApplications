package spa.samples.heartpatientmonitoring.domain.types.ecg;

import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.patient.medical.ClinicalTestResult;

public interface ECG extends ECGraw, ClinicalTestResult {	
	
	
	public ECGAnalysisReport getAnalysisReport();
	
	public void setAnalysisReport(ECGAnalysisReport analysisReport);
	
	public ECGProcessingState getProcessingState();
	
	public void setProcessingState(ECGProcessingState processingState);
		
	public HeartMonitorDevice getDevice();

}