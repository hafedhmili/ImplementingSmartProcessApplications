/**
 * 
 */
package spa.samples.health.impl;

import java.time.Instant;
import java.util.Iterator;

import spa.samples.health.domain.Activity;
import spa.samples.health.domain.TimeSequence;
import spa.samples.health.domain.TimedEvent;

/**
 * Author: Hafedh Mili
 */
public class TimeSequenceImpl<T extends TimedEvent> implements TimeSequence<T> {

	@Override
	public void addEvent(T element, Instant timeStamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvent(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getEventAtTime(Instant time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEventClosestToTime(Instant time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getMostRecentEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getEventsOccurringPriorTo(Instant time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getEventsOccurringAfter(Instant time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getEventsOccurringBetween(Instant start, Instant end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Activity> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}
