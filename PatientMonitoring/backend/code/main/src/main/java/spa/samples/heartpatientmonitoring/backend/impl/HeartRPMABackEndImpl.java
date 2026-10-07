package spa.samples.heartpatientmonitoring.backend.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Queue;

import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.Message;
import com.microsoft.azure.sdk.iot.service.ServiceClient;

import spa.samples.heartpatientmonitoring.backend.types.BRComponentBinding;
import spa.samples.heartpatientmonitoring.backend.types.BackEndFactory;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.backend.types.HeartMonitoringDeviceMessage;
import spa.samples.heartpatientmonitoring.backend.types.IoTHubBinding;
import spa.samples.heartpatientmonitoring.backend.types.MLComponentBinding;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECG;

public class HeartRPMABackEndImpl implements spa.samples.heartpatientmonitoring.backend.types.HeartRPMABackEnd {

    private Queue<ECG> toBeProcessedECGsQueue;

    private Queue<ECG> processedECGsQueue;

    private Duration step;

    private RecordingModality recordingModality;

    private HeartMonitorDevice heartMonitoringDevice;

    private BRComponentBinding brComponentBinding;

    private MLComponentBinding mlComponentBinding;

    private IoTHubBinding iotHubBinding;

    private ServiceClient serviceClient;

    public HeartRPMABackEndImpl(String deviceId) {
        this.heartMonitoringDevice = BackEndFactory.getSingletonInstance().createHeartMonitoringDevice(deviceId);
        this.toBeProcessedECGsQueue = new java.util.concurrent.ConcurrentLinkedQueue<ECG>();
        this.processedECGsQueue = new java.util.concurrent.ConcurrentLinkedQueue<ECG>();
    }



    @Override
    public void setStep(Duration step) {
        this.step = step;
        
    }

    @Override
    public Duration getStep() {
        return this.step;
    }

    @Override
    public boolean isNotAligned(ECG oldECG,
            Instant oldTime, ECG latestECG,
            Instant latestTime) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void handleDifference(ECG oldECG,
            Instant oldTime, ECG latestECG,
            Instant latestTime) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isProblematic(ECG latestECG,
            Instant latestTime) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void handleProblematicECG(ECG latestECG, Instant latestTime) {
        // 1.  Call the BR component to handle the problematic ECG
        //

        // 2.  Add the latest ECG to the queue of processed ECGs
        processedECGsQueue.add(latestECG);
    }

    @Override
    public boolean needsToCheck(ECG latestECG, Instant timeBeforeLast, Instant latestTime) {
        return true;
    }

    @Override
    public void newECG(ECG latestECG) {
        //  1.  Add the latest ECG to the queue of ECGs to be processed
        toBeProcessedECGsQueue.add(latestECG);
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public ECG getNextECG() {
        if (toBeProcessedECGsQueue.isEmpty()) {
            return null;
        } else {
            ECG nextECG = toBeProcessedECGsQueue.poll();
            return nextECG;
        }
    }

    @Override
    public Iterator<ECG> getECGBacklog() {
        return toBeProcessedECGsQueue.iterator();
    }

    @Override
    public HeartMonitorDevice getHeartMonitoringDevice() {
        return heartMonitoringDevice;
    }

    @Override
    public void setECGRecordingModality(RecordingModality modality) {
        this.recordingModality = modality;
    }

    @Override
    public void setRecordingModality(int howManyTimes, Duration perWhatPeriod, Duration howLong) {
        this.recordingModality = new RecordingModality(howManyTimes, perWhatPeriod, howLong);
    }    

    @Override
    public RecordingModality getECGRecordingModality() {
        return this.recordingModality;
    }

    @Override
    public BRComponentBinding getBRComponentBinding() {
        return brComponentBinding;
    }

    @Override
    public MLComponentBinding getMLComponentBinding() {
        return mlComponentBinding;
    }

    @Override
    public void setBRComponentBinding(BRComponentBinding brComponentBinding) {
        this.brComponentBinding = brComponentBinding;
    }

    @Override
    public void setMLComponentBinding(MLComponentBinding mlComponentBinding) {
        this.mlComponentBinding = mlComponentBinding;
    }

    @Override
    public void connectToAzureIoTHub() throws IOException, URISyntaxException {
        //  1.  Get the connection string and protocol from the IoTHubBinding record
        String connectionString = iotHubBinding.connectionString();
        IotHubServiceClientProtocol protocol = iotHubBinding.protocol();

        //  2.  Create a new ServiceClient using the connection string and protocol
        this.serviceClient = ServiceClient.createFromConnectionString(connectionString, protocol);

        //  3.  Open the ServiceClient
        serviceClient.open();
    }

    @Override
    public void sendMessageToAzureIoTHub(Message message) throws IOException, URISyntaxException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessageToAzureIoTHub'");
    }

    @Override
    public void setIoTHubBinding(IoTHubBinding iotHubBinding) {
        this.iotHubBinding = iotHubBinding;
    }

    @Override
    public IoTHubBinding getIoTHubBinding() {
        return this.iotHubBinding;
    }

    @Override
    public void sendMessageToHeartMonitoringDevice(HeartMonitorDevice device, HeartMonitoringDeviceMessage message)
            throws IOException, URISyntaxException {
        //  1.  I need to serialize the message into a import com.microsoft.azure.sdk.iot.service.Message;
        Message iotHubMessage = new Message(message.toString());

        //  2.  I need to make sure that I am connected to the Azure IoT Hub
        if (serviceClient != null) {
            this.connectToAzureIoTHub();
        }

        //  2.  I need to send the message to the device using the Azure IoT Hub
        try {
            serviceClient.send(device.getDeviceID(), iotHubMessage);
            System.out.println("Successfully sent message " + iotHubMessage + " to device with ID " + device.getDeviceID() + " via Azure IoT Hub: ");
        } catch (Exception e) {
            System.err.println("Error sending message to Azure IoT Hub: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ServiceClient getServiceClient() {
       return this.serviceClient;
    }
    
}
