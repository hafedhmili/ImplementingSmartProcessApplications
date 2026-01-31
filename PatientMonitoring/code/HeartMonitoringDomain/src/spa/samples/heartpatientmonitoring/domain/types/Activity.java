package spa.samples.heartpatientmonitoring.domain.types;

import java.time.Instant;

public record Activity(Patient patient, ActivityType activityType, IntensityLevel intensityLevel, Instant startTime, Instant endTime, Location startLocation, Location endLocation) {

}
