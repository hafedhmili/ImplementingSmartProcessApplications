/**
 * 
 */
package spa.samples.health.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

/**
 * @author hafedhmili
 *
 */
public interface Patient {
	
	/**
	 * 
	 * @return first name of patient
	 */
	public String getFirstName();
	
	/**
	 * 
	 * @return last name of patient
	 */
	public String getLastName();
	
	/**
	 * 
	 * @return social security number
	 */
	public String getSsn();
	
	/**
	 * Returns the birthdate of the patient
	 * @return
	 */
	public Instant getBirthDate();
	
	/**
	 * modifies ssn. Can happen when Patient changes residency status
	 * @param ssn
	 */
	public void setSsn(String ssn);
	
	/**
	 * return the address of the patient (where they live). The patient may or may
	 * not be at the address at any given point in time
	 * @return
	 */
	public Address getAddress();
	
	/**
	 * changes the address of a patient
	 * @param anAddress
	 */
	public void setAddress(Address anAddress);
	
	
	/**
	 * 
	 */
	public Location getCurrentLocation();
	
	/**
	 * The Patient object keep track of the various health conditions of the Patient.
	 * This is the method to add a <code>HealthCondition</code> to the patient's file
	 * @param hCond
	 */
	public void addHealthCondition(HealthCondition hCond);
	
	/**
	 * 
	 * @param hCond
	 * @return
	 */
	public HealthCondition removeHealthCondition(HealthCondition hCond);
	
	/**
	 * returns list of health conditions
	 * @return
	 */
	public Iterator<HealthCondition> getHealthConditions();
	
	/**
	 * tests whether patient has a heart condition
	 * @return
	 */
	public boolean hasHeartCondition();
	
	/**
	 * Returns the last reading on record of a particular <code>ReadingType</code>, e.g.
	 * pulse or blood pressure, or whatever we are monitoring
	 * @param type
	 * @return
	 */
	public Reading getLastReading(ReadingType type);
	
	/**
	 * Returns the reading of type <code>inst</code> closest to the 
	 * <code>Instant</code> <code>inst</code>
	 * @param type : the type of reading requested
	 * @param inst : the instant around which I seek the reading
	 * @return
	 */
	public Reading getReadingAroundTime(ReadingType type, Instant inst);
	
	/**
	 * Records a new <code>Reading</code> (parameter <code>reading</code>)), which
	 * will include the type, value, and instant of reading
	 * @param reading
	 */
	public void addReading(Reading reading);
	
	/**
	 * Returns the set of readings of type <code>type</code> that were made within
	 * the past <code>duration</code>. 
	 * @param type
	 * @param duration
	 * @return
	 */
	public Iterator<Reading> getReadingsForPast(ReadingType type, Duration duration );
	
	/**
	 * Returns the set of readings of type <code>type</code> since <code>Instant</code>
	 * <code>instant</code>. 
	 * @param type
	 * @param instant
	 * @return
	 */
	public Iterator<Reading> getReadingsSince(ReadingType type, Instant instant );
	
	/**
	 * Raises an <code>Alert</code>. about the patient, based on the reading.
	 * @param alert
	 */
	public Alert raiseAlert(AlertType alertType, Reading reading);
	
	
	/**
	 * returns the list of past activities of the patient, including
	 * the ongoing ones
	 * @return
	 */
	public Iterator<Activity>  getAllActivities();	
	
	public Iterator<Activity> getActivitiesOfType(ActivityType activityType);
	
	
	/**
	 * Returns the list of ongoing activities. It is the activities that
	 * have started but that haven't ended yet.
	 * 
	 * @return
	 */
	public Iterator<Activity> getAllCurrentActivities();
	
	/**
	 * We assume that there is a single ongoing activity of any type (running,
	 * eating, swimming, sleeping)
	 * 
	 * @param activityType
	 * @return
	 */
	public Activity getCurrentActivityOfType(ActivityType activityType);
	
	/**
	 * returns the current state of the patient.
	 * @return
	 */
	public PatientState getCurrentState();
	
	/**
	 * transitions the patient to a new state. Ideally, I would have liked
	 * to pass only some attributes related to the next state, and have the
	 * method create the new state and link the new state with the current one
	 * @param nextState
	 */
	public void transitionToState(PatientState nextState);

	/**
	 * transitions the patient to a new state characterized by a new
	 * state label. Implementations of this method will know what to
	 * keep from current state and what to change or reset
	 * 
	 * @param nextStateLabel
	 */
	public void transitionToStateWithType(PatientStateType nextStateLabel);
}


