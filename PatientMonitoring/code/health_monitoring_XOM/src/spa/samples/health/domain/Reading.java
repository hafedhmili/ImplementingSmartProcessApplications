
package spa.samples.health.domain;

public interface Reading extends TimedEvent {
	
	/**
	 * return the type of reading
	 * @return
	 */
	public ReadingType getType();
	

	/**
	 * return the patient for whom this reading
	 * was taken
	 * @return
	 */
	public Patient getPatient();
	
	/**
	 * return the actual value of the reading. It is a complex
	 * object consisting of a value and a unit. See
	 * class <code>ReadingValue</code>
	 * @return
	 */
	public ReadingValue getValue();
	
	/**
	 * The location where the reading was made
	 * @return
	 */
	public Location getReadingLocation();
	
	/**
	 * return the device that made the reading
	 * @return
	 */
	public Device getReadingDevice();
	
	/**
	 * returns the processing state of the current reading.
	 * When a reading is first encountered it is "to be processed".
	 * At the end of the processing cycle, it is either processed
	 * or ignored.
	 * @return
	 */
	public ProcessingState getProcessingState();
	
	/**
	 * sets the processing state of the current reading
	 * @param state
	 */
	public void setProcessingState(ProcessingState state);

}
