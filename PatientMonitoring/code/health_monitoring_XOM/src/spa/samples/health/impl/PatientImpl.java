/**
 * 
 */
package spa.samples.health.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import spa.samples.health.domain.Activity;
import spa.samples.health.domain.ActivityType;
import spa.samples.health.domain.Address;
import spa.samples.health.domain.Alert;
import spa.samples.health.domain.AlertType;
import spa.samples.health.domain.HealthCondition;
import spa.samples.health.domain.HealthConditionType;
import spa.samples.health.domain.Location;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.PatientState;
import spa.samples.health.domain.PatientStateType;
import spa.samples.health.domain.Reading;
import spa.samples.health.domain.ReadingType;
import spa.samples.health.domain.TimeSequence;
import spa.samples.health.util.AbstractFactory;

/**
 * Author: Hafedh Mili
 */
public class PatientImpl implements Patient {
	
	private String firstName;
	
	private String lastName;
	
	private String ssn;
	
	private Instant birthDate;
	
	private Address address;
	
	private Location location;
	
	//private Instant locationSettingTime;
	
	private PatientState currentState;
	
	private TimeSequence<PatientState> previousPatientStates;
	
	/**
	 * health conditions are indexed by <code>HealthConditionType</code> to make
	 * retrieval easier. A <code>HealthCondition</code> may have many episodes.
	 * They will all be
	 */
	private HashMap<HealthConditionType,HealthCondition> healthConditions;
	
	/**
	 * <code>readings</code> is a table of <code>TimeSequence</code>s,
	 * one for each <code>ReadingType</code>. 
	 * We assume that there is a single series of reading types 
	 */
	private HashMap<ReadingType,TimeSequence<Reading>> readings;
	
	private HashMap<ActivityType,TimeSequence<Activity>> activities;
	
	public PatientImpl(String fName, String lName, Instant bDay) {
		this.firstName = fName;
		this.lastName = lName;
		this.birthDate = bDay;
		this.previousPatientStates = new TimeSequenceImpl<PatientState>();
		this.activities = new HashMap<ActivityType,TimeSequence<Activity>>();
		this.healthConditions = new HashMap<HealthConditionType,HealthCondition>();
		this.readings = new HashMap<ReadingType,TimeSequence<Reading>> ();
	}
	
	/**
	 * Adding a no-arg constructor for testing purposes
	 */
	public PatientImpl() {
		
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getSsn() {
		return ssn;
	}

	@Override
	public Instant getBirthDate() {
		return birthDate;
	}

	@Override
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void setAddress(Address anAddress) {
		this.address = anAddress;
	}

	@Override
	public Location getCurrentLocation() {
		// we can make it more complicated later. For example, we can check
		// the locationSettingTime, and depending on the time elapsed
		// we can consider it obsolete, and find some other way of 
		// getting the location
		return location;
	}

	@Override
	public void addHealthCondition(HealthCondition hCond) {
		healthConditions.put(hCond.getType(), hCond);
	}

	@Override
	public HealthCondition removeHealthCondition(HealthCondition hCond) {
		if (healthConditions.containsValue(hCond)) {
			healthConditions.remove(hCond.getType(),hCond);
			return hCond;
		}
		return null;
	}

	@Override
	public Iterator<HealthCondition> getHealthConditions() {
		return healthConditions.values().iterator();
	}

	@Override
	public boolean hasHeartCondition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reading getLastReading(ReadingType type) {
		TimeSequence<Reading> readingsOfType = readings.get(type);
		return readingsOfType.getMostRecentEvent();
	}

	@Override
	public Reading getReadingAroundTime(ReadingType type, Instant inst) {
		TimeSequence<Reading> readingsOfType = readings.get(type);
		return readingsOfType.getEventClosestToTime(inst);
	}

	@Override
	public void addReading(Reading reading) {
		TimeSequence<Reading> readingsOfType = readings.get(reading.getType());
		readingsOfType.addEvent(reading, reading.getTimeOfOccurrence());
	}

	@Override
	public Iterator<Reading> getReadingsForPast(ReadingType type, Duration duration) {
		// find the start time of period
		Instant now = Instant.now();
		Instant startTime = (Instant)duration.subtractFrom(now);
		return this.getReadingsSince(type, startTime);


	}

	@Override
	public Iterator<Reading> getReadingsSince(ReadingType type, Instant instant) {
		TimeSequence<Reading> readingsOfType = readings.get(type);
		return readingsOfType.getEventsOccurringAfter(instant);
	}

	@Override
	public Alert raiseAlert(AlertType alertType, Reading reading) {
		Alert newAlert = AbstractFactory.getSingleton().createAlert(alertType, this, reading);
		return newAlert;
	}

	@Override
	public Iterator<Activity> getAllActivities() {
		Collection<Activity> allActivities = new ArrayList<Activity>();
		activities.forEach((k,v) -> {v.getAllEvents().forEachRemaining((activity) -> allActivities.add(activity));} );
		return allActivities.iterator();
	}

	@Override
	public Iterator<Activity> getAllCurrentActivities() {
		Collection<Activity> allCurrentActivities = new ArrayList<Activity>();
		
		// all current activities are activities with no end time
		activities.forEach((k,v) -> {v.getAllEvents().forEachRemaining((activity) -> {if (activity.getEndTime() != null ) allCurrentActivities.add(activity);});} );
		return allCurrentActivities.iterator();
	}

	@Override
	public Iterator<Activity> getActivitiesOfType(ActivityType activityType) {
		return activities.get(activityType).getAllEvents();
	}

	@Override
	public Activity getCurrentActivityOfType(ActivityType activityType) {
		Iterator<Activity> activitiesOfType = activities.get(activityType).getAllEvents();
		// Assuming there is a single on-going activity of any type, return the activity of
		// the appropriate type whose start time is before now, and whose end time
		// has not been set or has been scheduled for after now
		while (activitiesOfType.hasNext()) {
			Activity next = activitiesOfType.next();
			Instant now = Instant.now();
			if ((next.getStartTime().compareTo(now) < 0) && ((next.getEndTime() == null) || (next.getEndTime().compareTo(now) > 0) ))
				return next;
		}
		
		// if none is found, return null
		return null;
	}

	@Override
	public PatientState getCurrentState() {
		return currentState;
	}

	@Override
	public void transitionToState(PatientState nextState) {
		previousPatientStates.addEvent(currentState, currentState.getTimeOfOccurrence());
		currentState.setNextState(nextState);
		nextState.setPreviousState(currentState);
		currentState = nextState;
	}

	@Override
	public void transitionToStateWithType(PatientStateType nextStateLabel) {
		PatientState nextState = AbstractFactory.getSingleton().createPatientState(this, nextStateLabel, Instant.now());
		this.transitionToState(nextState);
	}
}
