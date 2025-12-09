/**
 * 
 */
package spa.samples.health.impl;

import java.time.Instant;

import spa.samples.health.domain.Alert;
import spa.samples.health.domain.AlertType;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.Reading;

/**
 * Author: Hafedh Mili
 */
public class AlertImpl implements Alert {
	
	/**
	 * the type of the alert
	 */
	private AlertType type;
	
	/**
	 * the patient concerned
	 */
	private Patient patient;
	
	/**
	 * the reading that triggered the alert
	 */
	private Reading reading;
	
	/**
	 * the time that the alert was created.
	 */
	private Instant alertTime;
	
	/**
	 * 
	 * @param type
	 * @param patient
	 * @param reading
	 */
	public AlertImpl(AlertType type, Patient patient, Reading reading) {
		this.patient = patient;
		this.type = type;
		this.reading = reading;
		this.alertTime = Instant.now();
		
	}

	@Override
	public Instant getAlertTime() {
		return this.alertTime;
	}

	@Override
	public Reading getReading() {
		return reading;
	}

	@Override
	public void setPatient(Patient pat) {
		this.patient = pat;
	}

	@Override
	public void setAlertType(AlertType type) {
		this.type = type;

	}

	@Override
	public void setReading(Reading reading) {
		this.reading = reading;
	}

	@Override
	public Patient getPatient() {
		return this.patient;
	}

	@Override
	public AlertType getAlertType() {
		return this.type;
	}

}
