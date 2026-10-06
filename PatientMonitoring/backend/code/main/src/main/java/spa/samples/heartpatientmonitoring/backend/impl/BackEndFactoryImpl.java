package spa.samples.heartpatientmonitoring.backend.impl;

import spa.samples.heartpatientmonitoring.backend.types.BRComponentBinding;
import spa.samples.heartpatientmonitoring.backend.types.BackEndFactory;
import spa.samples.heartpatientmonitoring.backend.types.HeartRPMABackEnd;
import spa.samples.heartpatientmonitoring.backend.types.IoTHubBinding;
import spa.samples.heartpatientmonitoring.backend.types.MLComponentBinding;

public class BackEndFactoryImpl implements BackEndFactory {
    private static BackEndFactoryImpl singletonInstance = null;

    public static BackEndFactoryImpl getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BackEndFactoryImpl();
        }
        return singletonInstance;
    }

    @Override
    public HeartRPMABackEnd createHeartRPMABackEnd(IoTHubBinding iotHubBinding) {
        HeartRPMABackEnd heartRPMABackEndImpl = new HeartRPMABackEndImpl();
        heartRPMABackEndImpl.setIoTHubBinding(iotHubBinding);
        return heartRPMABackEndImpl;
    }

    @Override
    public HeartRPMABackEnd createHeartRPMABackEnd(IoTHubBinding iotHubBinding, MLComponentBinding mlComponentBinding,
            BRComponentBinding brmsBinding) {

        HeartRPMABackEnd heartRPMABackEndImpl = createHeartRPMABackEnd(iotHubBinding);
        heartRPMABackEndImpl.setMLComponentBinding(mlComponentBinding);
        heartRPMABackEndImpl.setBRComponentBinding(brmsBinding);
        return heartRPMABackEndImpl;
    }
    
}
