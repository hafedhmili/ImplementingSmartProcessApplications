/**
 * 
 */
package spa.samples.health.impl;

import java.time.Instant;

import spa.samples.health.domain.Patient;
import spa.samples.health.domain.PatientState;
import spa.samples.health.domain.PatientStateType;

/**
 * Author: Hafedh Mili
 */
public class PatientStateImpl implements PatientState {

	@Override
	public Instant getTimeOfOccurrence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getPatient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientStateType getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instant getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instant getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEndTime(Instant endTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public PatientState getNextState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientState getPreviousState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNextState(PatientState ps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPreviousState(PatientState ps) {
		// TODO Auto-generated method stub

	}

}
