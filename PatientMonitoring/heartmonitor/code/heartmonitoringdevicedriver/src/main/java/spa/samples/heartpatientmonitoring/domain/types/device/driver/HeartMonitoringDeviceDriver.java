package spa.samples.heartpatientmonitoring.domain.types.device.driver;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;

public interface HeartMonitoringDeviceDriver {

    public HeartMonitorDevice getHeartMonitorDevice();

    public DeviceClient getHeartMonitorDeviceClient();

}