/**
 * 
 */
package spa.samples.health.impl;

import java.time.Instant;

import spa.samples.health.domain.Device;
import spa.samples.health.domain.Location;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.ProcessingState;
import spa.samples.health.domain.Reading;
import spa.samples.health.domain.ReadingType;
import spa.samples.health.domain.ReadingValue;

/**
 * Author: Hafedh Mili
 */
public class ReadingImpl implements Reading {
	
	private Instant timeOfOccurrence;
	
	private ReadingType type;

	private Patient patient;
	
	private Device device;
	
	private ReadingValue value;
	
	private ProcessingState processingState;
	
	public ReadingImpl(Patient patient, ReadingType type, ReadingValue value, Device device, Instant ...time) {
		this.patient = patient;
		this.type = type;
		this.value = value;
		this.device = device;
		if (time.length ==0) 
			this.timeOfOccurrence = Instant.now();
		else 
			this.timeOfOccurrence = time[0];
		processingState = ProcessingState.ToBeProcessed;
	}
	
	@Override
	public Instant getTimeOfOccurrence() {
		return timeOfOccurrence;
	}

	@Override
	public int compareTo(Object o) {
		// checks if o is also a reading. If not, return -1000
		// if the other object is reading:
		// a) if it was made by the same device, then return the result of compareTo() on
		// the time of occurrence
		// b) if it was made by a different device, return -1000
		if (! (o instanceof Reading)) return -1000;
		Reading otherReading = (Reading)o;
		if (!(this.getReadingDevice().equals(otherReading.getReadingDevice()))) return -1000;
		return this.getTimeOfOccurrence().compareTo(otherReading.getTimeOfOccurrence());
	}

	@Override
	public ReadingType getType() {
		return type;
	}

	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public ReadingValue getValue() {
		return value;
	}

	@Override
	public Location getReadingLocation() {
		return device.getCurrentLocation();
	}

	@Override
	public Device getReadingDevice() {
		return device;
	}

	@Override
	public ProcessingState getProcessingState() {
		return processingState;
	}

	@Override
	public void setProcessingState(ProcessingState state) {
		processingState = state;

	}

}
