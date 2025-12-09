package spa.samples.health.domain;

import java.time.Instant;

public interface HealthAct extends HealthEpisode {
	
	/**
	 * The type of health act. It could be different types of
	 * diagnostic types
	 * @return
	 */
	public ActType getActType();
	
	/**
	 * for the time being it is just a string indicating who
	 * performed the act
	 * @return
	 */
	public String getPerformedBy();

	/**
	 * returns the occurrence date of the health act. An act is instantaneous
	 * whereas an episode has duration. Practically, occurrence date is stored
	 * in the start date of HealthEpisode
	 * @return
	 */
	public Instant getOccurrenceDate();
}
