/**
 * 
 */
package spa.samples.health.impl;

import java.time.Instant;


import spa.samples.health.domain.ActType;
import spa.samples.health.domain.HealthAct;
import spa.samples.health.domain.Patient;

/**
 * Author: Hafedh Mili
 */
public class HealthActImpl extends HealthEpisodeImpl implements HealthAct {
	
	private String performedBy;

	/**
	 * @param patient
	 * @param episodeType
	 * @param startDate
	 */
	public HealthActImpl(Patient patient, ActType actType, Instant occurrenceDate, String performedBy) {
		super(patient,actType, occurrenceDate);
		this.performedBy = performedBy;
	}

	@Override
	public ActType getActType() {
		return (ActType) this.getEpisodeType();
	}

	@Override
	public String getPerformedBy() {
		return performedBy;
	}

	@Override
	public Instant getOccurrenceDate() {
		return this.getStartDate();
	}
}
