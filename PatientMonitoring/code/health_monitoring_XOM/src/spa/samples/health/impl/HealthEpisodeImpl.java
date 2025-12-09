package spa.samples.health.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import spa.samples.health.domain.HealthEpisode;
import spa.samples.health.domain.HealthEpisodeType;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.Reading;
import spa.samples.health.domain.Severity;

/**
 * Author: Hafedh Mili
 */
public class HealthEpisodeImpl implements HealthEpisode {
	
	private HealthEpisodeType episodeType;
	
	private Instant startDate;
	
	private Instant endDate;
	
	private Severity severity;
	
	private Patient patient;
	
	private Collection<Reading> associatedReadings;
	
	public HealthEpisodeImpl(Patient patient, HealthEpisodeType episodeType, Instant startDate) {
		this.patient = patient;
		this.episodeType = episodeType;
		this.startDate = startDate;
		associatedReadings = new ArrayList<Reading>();
	}
	
	public HealthEpisodeImpl(Patient patient, HealthEpisodeType episodeType, Instant startDate, Instant endDate, Severity severity) {
		this(patient,episodeType,startDate);
		this.endDate = endDate;
		this.severity = severity;
	}
	
	@Override
	public Patient getPatient() {
		return patient;
	}

	@Override
	public HealthEpisodeType getEpisodeType() {
		return episodeType;
	}

	@Override
	public Instant getStartDate() {
		return startDate;
	}

	@Override
	public Instant getEndDate() {
		return endDate;
	}

	@Override
	public Duration getDuration() {
		return Duration.between(startDate,endDate);
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public Iterator<Reading> getReadings() {
		return associatedReadings.iterator();
	}

	@Override
	public void addAssociatedReading(Reading reading) {
		associatedReadings.add(reading);
	}

	@Override
	public void removeAssociatedReading(Reading reading) {
		associatedReadings.remove(reading);
	}

}
