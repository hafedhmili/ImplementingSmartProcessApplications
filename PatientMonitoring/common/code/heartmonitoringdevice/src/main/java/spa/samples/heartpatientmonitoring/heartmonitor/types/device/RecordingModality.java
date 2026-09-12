package spa.samples.heartpatientmonitoring.heartmonitor.types.device;

import java.time.Duration;

public record RecordingModality(int frequency, Duration referencePeriod, Duration duration) {
	
	public String toString() {
		return ""+ frequency + "ECGs of " + duration + " per " + referencePeriod;
	}

}
