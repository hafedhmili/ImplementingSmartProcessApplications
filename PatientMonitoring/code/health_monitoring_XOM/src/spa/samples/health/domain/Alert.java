package spa.samples.health.domain;

import java.time.Instant;

public interface Alert {
	
	/**
	 * return the time the alert
	 * @return
	 */
	public Instant getAlertTime();
	
	/**
	 * return the reading that triggered the alert.
	 * This may be the last in a series of readings 
	 * that triggered the alert
	 * @return
	 */
	public Reading getReading();
	
	/**
	 * the patient concerned by the alert
	 * @return
	 */
	public  void setPatient(Patient pat);
	
	/**
	 * the type of alert
	 */
	public void setAlertType(AlertType type);
	
	/**
	 * return the reading that triggered the alert.
	 * This may be the last in a series of readings 
	 * that triggered the alert
	 * @return
	 */
	public void setReading(Reading reading);
	
	/**
	 * the patient concerned by the alert
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * the type of alert
	 * @return
	 */
	public AlertType getAlertType();

}
