package spa.samples.heartpatientmonitoring.domain.types.ecg;

import spa.samples.heartpatientmonitoring.domain.types.patient.medical.ClinicalTestResult;

public interface ECG extends ClinicalTestResult {	
	
	
	public ECGAnalysisReport getAnalysisReport();
	
	public void setAnalysisReport(ECGAnalysisReport analysisReport);
	
	public ECGProcessingState getProcessingState();
	
	public void setProcessingState(ECGProcessingState processingState);

}