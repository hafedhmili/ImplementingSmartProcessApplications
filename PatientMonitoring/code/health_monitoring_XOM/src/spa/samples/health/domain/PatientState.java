package spa.samples.health.domain;

import java.time.Instant;

/**
 * represents the state of the patient. Right now it is
 * limited to a label, indicating severity of state, and begin time and
 * end time. 
 * 
 * I have not thought through what else we need to capture about the 
 * state of the patient for now.
 * @author mili_h
 *
 */
public interface PatientState extends TimedEvent {
	
	/**
	 * returns the ID of the state
	 * @return
	 */
	public String getID();
	
	/**
	 * The patient whose state this is
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * return the label of state. It goes from <code>Normal,/code> to
	 * get your arse to the emergency
	 * @return
	 */
	public PatientStateType getLabel();
	
	/**
	 * the start time for this state. For all practical purposes
	 * iot is the instantiation time
	 * @return
	 */
	public Instant getStartTime();
	
	/**
	 * the end time of the state. For all practical purposes,
	 * it corresponds to the time of creation of the next state
	 * @return
	 */
	public Instant getEndTime();
	
	public void setEndTime(Instant endTime);

	public PatientState getNextState();
	
	public PatientState getPreviousState();
	
	public void setNextState(PatientState ps);
	
	public void setPreviousState(PatientState ps);
}
