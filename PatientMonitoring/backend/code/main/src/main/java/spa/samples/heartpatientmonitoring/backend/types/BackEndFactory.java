package spa.samples.heartpatientmonitoring.backend.types;

import spa.samples.heartpatientmonitoring.backend.impl.BackEndFactoryImpl;

public interface BackEndFactory {
    
    public HeartRPMABackEnd createHeartRPMABackEnd(IoTHubBinding iotHubBinding);

    public HeartRPMABackEnd createHeartRPMABackEnd(IoTHubBinding iotHubBinding, MLComponentBinding mlComponentBinding, BRComponentBinding brmsBinding);

    public static BackEndFactory getSingletonInstance() {
        return BackEndFactoryImpl.getSingletonInstance();
    }
}
