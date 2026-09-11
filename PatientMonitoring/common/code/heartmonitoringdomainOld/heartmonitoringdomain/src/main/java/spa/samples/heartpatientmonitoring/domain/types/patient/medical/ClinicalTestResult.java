package spa.samples.heartpatientmonitoring.domain.types.patient.medical;


import spa.samples.heartpatientmonitoring.domain.types.patient.Patient;
import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;

public interface ClinicalTestResult extends Measurement {

	public Patient getPatient();

}
