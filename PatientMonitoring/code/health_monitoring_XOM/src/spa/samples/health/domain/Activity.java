package spa.samples.health.domain;

import java.time.Duration;
import java.time.Instant;

/**
 * Represents whatever the patient is doing.
 * 
 * It is extending TimedEvent, based on the start time, so that we
 * can sort activities of the same type (e.g. running) based
 * on the start time
 * @author hafedhmili
 *
 */
public interface Activity extends TimedEvent {
	
	/**
	 * returns the intensity level of the activity
	 * @return
	 */
	public IntensityLevel getIntensity();
	
	/**
	 * returns the type of activity (eating, sleeping, running, etc.)
	 * @return
	 */
	public ActivityType getActivityType();
	
	/**
	 * returns the start time of the activity
	 * @return
	 */
	public Instant getStartTime();
	
	
	/**
	 * returns the end time of the activity--if it has ended. If
	 * the function returns null, we assume that the activity is still
	 * ongoing
	 * @return
	 */
	public Instant getEndTime();
	
	/**
	 * returns the patient
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * returns the duration of the activity
	 * @return
	 */
	public Duration getDuration();
	
	/**
	 * starts the activity. This will simply set the start time
	 * to now.
	 */
	public void start();
	
	/**
	 * ends the activity. This sets the end time to now
	 */
	public void end();

}
