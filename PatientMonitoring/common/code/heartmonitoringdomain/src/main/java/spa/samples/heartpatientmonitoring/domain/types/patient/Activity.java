package spa.samples.heartpatientmonitoring.domain.types.patient;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.patient.medical.IntensityLevel;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;

public record Activity(Patient patient, ActivityType activityType, IntensityLevel intensityLevel, Instant startTime, Instant endTime, Location startLocation, Location endLocation) {

}
