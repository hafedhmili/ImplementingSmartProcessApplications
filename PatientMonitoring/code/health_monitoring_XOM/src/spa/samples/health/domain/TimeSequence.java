/**
 * 
 */
package spa.samples.health.domain;

import java.time.Instant;
import java.util.Iterator;

/**
 * Author: Hafedh Mili
 */
public interface TimeSequence <T extends TimedEvent>{
	
	public void addEvent(T element, Instant timeStamp);
	
	public void removeEvent(T element);
	
	public T getEventAtTime(Instant time);
	
	public T getEventClosestToTime(Instant time);
	
	public T getMostRecentEvent();
	
	public Iterator<T> getEventsOccurringPriorTo(Instant time);
	
	public Iterator<T> getEventsOccurringAfter(Instant time);
	
	public Iterator<T> getEventsOccurringBetween(Instant start, Instant end);

	/**
	 * @return
	 */
	public Iterator<Activity> getAllEvents();
}
