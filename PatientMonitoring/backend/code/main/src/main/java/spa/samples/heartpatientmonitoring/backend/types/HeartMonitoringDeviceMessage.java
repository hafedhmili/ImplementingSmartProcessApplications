package spa.samples.heartpatientmonitoring.backend.types;

import java.time.Duration;

import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;

/**
 * HeartMonitoringDeviceMessage
 */
public record HeartMonitoringDeviceMessage(DeviceState stateCommand, int howManyECGs, Duration perWhatPeriod, Duration howLongECGDuration) {

}
