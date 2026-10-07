package spa.samples.heartpatientmonitoring.backend.impl;

import spa.samples.heartpatientmonitoring.backend.types.BRComponentBinding;
import spa.samples.heartpatientmonitoring.backend.types.BackEndFactory;
import spa.samples.heartpatientmonitoring.backend.types.HeartRPMABackEnd;
import spa.samples.heartpatientmonitoring.backend.types.IoTHubBinding;
import spa.samples.heartpatientmonitoring.backend.types.MLComponentBinding;
import spa.samples.heartpatientmonitoring.heartmonitor.impl.device.HeartMonitorDeviceImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;

public class BackEndFactoryImpl implements BackEndFactory {
    private static BackEndFactoryImpl singletonInstance = null;

    public static BackEndFactoryImpl getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BackEndFactoryImpl();
        }
        return singletonInstance;
    }

    @Override
    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID, IoTHubBinding iotHubBinding) {
        HeartRPMABackEnd heartRPMABackEndImpl = createHeartRPMABackEnd(deviceID);
        heartRPMABackEndImpl.setIoTHubBinding(iotHubBinding);
        return heartRPMABackEndImpl;
    }

    @Override
    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID, IoTHubBinding iotHubBinding, MLComponentBinding mlComponentBinding,
            BRComponentBinding brmsBinding) {

        HeartRPMABackEnd heartRPMABackEndImpl = createHeartRPMABackEnd(deviceID, iotHubBinding);
        heartRPMABackEndImpl.setMLComponentBinding(mlComponentBinding);
        heartRPMABackEndImpl.setBRComponentBinding(brmsBinding);
        return heartRPMABackEndImpl;
    }

    @Override
    public HeartMonitorDevice createHeartMonitoringDevice(String deviceId) {
       HeartMonitorDevice heartMonitorDevice = new HeartMonitorDeviceImpl(deviceId);
       return heartMonitorDevice;
    }

    @Override
    public HeartRPMABackEnd createHeartRPMABackEnd(String deviceID) {
        HeartRPMABackEnd heartRPMABackEndImpl = new HeartRPMABackEndImpl(deviceID);
        return heartRPMABackEndImpl;
    }
}