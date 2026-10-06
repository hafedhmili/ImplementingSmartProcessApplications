package spa.samples.heartpatientmonitoring.backend;

import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.ServiceClient;

import spa.samples.heartpatientmonitoring.backend.types.BackEndFactory;
import spa.samples.heartpatientmonitoring.backend.types.HeartRPMABackEnd;
import spa.samples.heartpatientmonitoring.backend.types.IoTHubBinding;

public class Main {

    public static String IOT_HUB_SERVICE_CONNECTION_STRING = "HostName=IOT-hub-for-heart-monitors.azure-devices.net;SharedAccessKeyName=service;SharedAccessKey=KeVjb4UMwwgv5/yiNXTXXKGVuVDYe6cMIAIoTCsuEyk=";

    public static IotHubServiceClientProtocol IOT_HUB_SERVICE_CONNECTION_PROTOCOL = IotHubServiceClientProtocol.AMQPS;
    public static void main(String[] args) {
        System.out.println("Hello world!");

        BackEndFactory backendFactory = BackEndFactory.getSingletonInstance();

        //  1.  Create an IoTHubBinding using the statically declared connection string and protocol
        IoTHubBinding iotHubBinding = new IoTHubBinding(IOT_HUB_SERVICE_CONNECTION_STRING, IOT_HUB_SERVICE_CONNECTION_PROTOCOL);

        //  2.  Create a HeartRPMABackEnd using the IoTHubBinding
        HeartRPMABackEnd heartRPMABackEnd = backendFactory.createHeartRPMABackEnd(iotHubBinding);

        //  3.  Connect to the Azure IoT Hub
        try {
            heartRPMABackEnd.connectToAzureIoTHub();
            ServiceClient serviceClient = heartRPMABackEnd.getServiceClient();
            System.out.println("Successfully connected to Azure IoT Hub using ServiceClient: " + serviceClient);
        } catch (Exception e) {
            System.err.println("Error connecting to Azure IoT Hub: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}