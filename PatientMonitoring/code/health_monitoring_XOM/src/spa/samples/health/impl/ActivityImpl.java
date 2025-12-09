
package spa.samples.health.impl;

import java.time.Duration;
import java.time.Instant;

import spa.samples.health.domain.Activity;
import spa.samples.health.domain.ActivityType;
import spa.samples.health.domain.IntensityLevel;
import spa.samples.health.domain.Patient;

public class ActivityImpl implements Activity {
	
	/**
	 * intensity of activity
	 */
	private IntensityLevel intensityLevel;
	
	/**
	 * type of activity
	 */
	private ActivityType activityType;
	
	/**
	 * activity start time. 
	 */
	private Instant startTime;
	
	/**
	 * activity end time
	 */
	private Instant endTime;
	
	/**
	 * the patient whose activity we are tracking
	 */
	private Patient patient;
	

	/**
	 * @param activityType
	 * @param patient
	 */
	public ActivityImpl(ActivityType activityType, Patient patient) {
		this.activityType = activityType;
		this.patient = patient;
	}

	@Override
	public IntensityLevel getIntensity() {
		return intensityLevel;
	}

	@Override
	public ActivityType getActivityType() {
		return activityType;
	}

	@Override
	public Instant getStartTime() {
		return startTime;
	}

	@Override
	public Instant getEndTime() {
		return endTime;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public Duration getDuration() {
		// if activity ended, duration is difference between
		// startTime and endTime
		if (endTime != null) return Duration.between(startTime,endTime);
		
		// else, it is the difference between startTime and now
		if (startTime != null) return Duration.between(startTime, Instant.now());
		return null;
	}

	@Override
	public void start() {
		startTime = Instant.now();

	}

	@Override
	public void end() {
		endTime = Instant.now();

	}

	@Override
	public Instant getTimeOfOccurrence() {
		return this.getStartTime();
	}

	@Override
	public int compareTo(Object o) {
		return this.getTimeOfOccurrence().compareTo(((Activity)o).getTimeOfOccurrence());
	}

}
