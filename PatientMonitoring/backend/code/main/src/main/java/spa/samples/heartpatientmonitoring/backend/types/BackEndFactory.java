package spa.samples.heartpatientmonitoring.backend.types;

import spa.samples.heartpatientmonitoring.backend.impl.BackEndFactoryImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;

public interface BackEndFactory {

    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID);

    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID, IoTHubBinding iotHubBinding);

    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID, IoTHubBinding iotHubBinding, MLComponentBinding mlComponentBinding, BRComponentBinding brmsBinding);

    public static BackEndFactory getSingletonInstance() {
        return BackEndFactoryImpl.getSingletonInstance();
    }

    /**
     * This method will create a HeartMonitorDevice "proxy" for an actual device registered within IoT hub that has the specified deviceId.  
     * We don't need to know more about the device, since we will communicate with it using messages. If the message does not make sense to
     * the receiver, that is too bad.
     * 
    * @param deviceId
     * @return
     */
    public HeartMonitorDevice createHeartMonitoringDevice(String deviceId);
}
