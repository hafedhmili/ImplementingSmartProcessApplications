package spa.samples.heartpatientmonitoring.backend;

import java.time.Duration;

import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.ServiceClient;

import spa.samples.heartpatientmonitoring.backend.types.BackEndFactory;
import spa.samples.heartpatientmonitoring.backend.types.HeartMonitoringDeviceMessage;
import spa.samples.heartpatientmonitoring.backend.types.HeartRPMABackEnd;
import spa.samples.heartpatientmonitoring.backend.types.IoTHubBinding;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;

public class Main {

    public static String IOT_HUB_SERVICE_CONNECTION_STRING = "HostName=IOT-hub-for-heart-monitors.azure-devices.net;SharedAccessKeyName=service;SharedAccessKey=KeVjb4UMwwgv5/yiNXTXXKGVuVDYe6cMIAIoTCsuEyk=";

    public static IotHubServiceClientProtocol IOT_HUB_SERVICE_CONNECTION_PROTOCOL = IotHubServiceClientProtocol.AMQPS;

    public static String IOT_HUB_DEVICE_ID = "heart-monitor-PID-04048";
    public static void main(String[] args) {
        System.out.println("Hello world!");

        BackEndFactory backendFactory = BackEndFactory.getSingletonInstance();

        //  1.  Create an IoTHubBinding using the statically declared connection string and protocol
        IoTHubBinding iotHubBinding = new IoTHubBinding(IOT_HUB_SERVICE_CONNECTION_STRING, IOT_HUB_SERVICE_CONNECTION_PROTOCOL);

        //  2.  Create a HeartRPMABackEnd using the IoTHubBinding and the device ID of the heart monitoring device that we want to communicate with
        HeartRPMABackEnd heartRPMABackEnd = backendFactory.createHeartRPMABackEnd(IOT_HUB_DEVICE_ID, iotHubBinding);

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

        //  4.  Send a message to the heart monitoring device using the HeartRPMABackEnd
        //      a.  Tell the heart monitor to record 2 ECGs per 24 hours, 3 minutes long each
        HeartMonitoringDeviceMessage message = new HeartMonitoringDeviceMessage(DeviceState.Recording, 2,Duration.ofHours(24),Duration.ofMinutes(3));
        try {
            HeartMonitorDevice heartMonitorDevice = heartRPMABackEnd.getHeartMonitoringDevice();
            heartRPMABackEnd.sendMessageToHeartMonitoringDevice(heartMonitorDevice, message);
            System.out.println("Successfully sent message to heart monitoring device: " + message);
        } catch (Exception e) {
            System.err.println("Error sending message to heart monitoring device: " + e.getMessage());
            e.printStackTrace();
        }
    }
}