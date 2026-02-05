package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;
import java.util.Iterator;

public interface PatientState {

	/**
	 * @return
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<ClinicalTestResult> getClinicalTestResults();


	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean addClinicalTestResult(ClinicalTestResult e);


	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public ClinicalTestResult removeClinicalTestResult(ClinicalTestResult e);

	/**
	 * @return the startDate
	 */
	public Instant getStartDate() ;

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Instant startDate) ;

	/**
	 * @return the endDate
	 */
	public Instant getEndDate() ;

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Instant endDate) ;

	/**
	 * @return the startLocation
	 */
	public Location getStartLocation() ;

	/**
	 * @param startLocation the startLocation to set
	 */
	public void setStartLocation(Location startLocation) ;

	/**
	 * @return the endLocation
	 */
	public Location getEndLocation() ;

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(Location endLocation);

	/**
	 * @return the previousState
	 */
	public PatientState getPreviousState();

	/**
	 * @param previousState the previousState to set
	 */
	public void setPreviousState(PatientState previousState) ;

	/**
	 * @return the label
	 */
	public PatientStateLabel getLabel() ;

}
