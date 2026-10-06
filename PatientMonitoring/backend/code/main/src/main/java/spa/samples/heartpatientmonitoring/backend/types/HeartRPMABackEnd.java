package spa.samples.heartpatientmonitoring.backend.types;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECG;
import com.microsoft.azure.sdk.iot.service.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface HeartRPMABackEnd {

    /**
     * This method sets the step for the processing loop of the HeartRPMABackEnd. 
     * The step is the pace of the processing loop, i.e. the sleep time between
     * two iterations through the loop.
     * @param step
     */
    public void setStep(Duration step);

    /**
     * This method returns the step for the processing loop
     * @return
     */
    public Duration getStep();

    /**
     * This method checks if the latest ECG is aligned with the old ECG. If it is not aligned, 
     * it will return true, otherwise it will return false.  It does it work by calling the 
     * business rule component
     * @param oldECG
     * @param oldTime
     * @param latestECG
     * @param latestTime
     * @return
     */
    public boolean isNotAligned(ECG oldECG, Instant oldTime, ECG latestECG, Instant latestTime);

    /**
     * This method will handle the difference between the old ECG and the latest ECG. 
     * It will be called when isNotAligned returns true. It will call the business rule component to 
     * handle the difference.
     * @param oldECG
     * @param oldTime
     * @param latestECG
     * @param latestTime
     */
    public void handleDifference(ECG oldECG, Instant oldTime, ECG latestECG, Instant latestTime);

    /**
     * This method will check if the latest ECG is problematic. It will call the machine learning model
     * to classify the latest ECG as problematic or not.
     * 
     * @param latestECG
     * @param latestTime
     * @return
     */
    public boolean isProblematic(ECG latestECG, Instant latestTime);

    /**
     * This method will handle the latest ECG if it is classified as problematic. It will 
     * (definitely) call the business rule component.
     * 
     * @param latestECG
     * @param latestTime
     */
    public void handleProblematicECG(ECG latestECG, Instant latestTime);

    /**
     * This method will check if we need to retrieve and 
     * @param latestECG
     * @param timeBeforeLast
     * @param latestTime
     * @return
     */
    public boolean needsToCheck(ECG latestECG, Instant timeBeforeLast, Instant latestTime);

    /**
     * This is the method that will be called by the event Grid when a new ECG arrives. This method
     * will add the new ECG to a processing queue for the HeartRPMABackEnd.
     * 
     * This will be implemented in a separate thread from the start() method so that we are able
     * to record the arrival of new ECGs while the processing loop is running.
     * @param latestECG
     */
    public void newECG(ECG latestECG);

    /**
     * This is the method that starts the processing loop of the HeartRPMABackEnd. It will start a thread
     * that will implement the processing loop.
     */
    public void start();

    /**
     * This method will return the next ECG that needs to be processed by the HeartRPMABackEnd. It will 
     * retrieve that the ECG that is at the top of the ECG queue. 
     * @return
     */
    public ECG getNextECG();

    /**
     * This method will return an iterator over the ECG backlog. It is an interator
     * over the contents of the ECG queue.
     * @return
     */
    public Iterator<ECG> getECGBacklog();

    /**
     * This method will return the HeartMonitoringDevice 
     * that is being currently used.
     * 
     * The application follows the health of both the patient and the device--among
     * other things to figure out whether to take its readings seriously.
     * @return
     */
    public HeartMonitoringDevice getHeartMonitoringDevice();

    /**
     * This method will set the recording modality for the ECG. This method will be called
     * when a heart monitor is connected to the system. The recording modality will be adjusted
     * based on the first set of ECGs, whether an ECG is significantly different from the previous one, 
     * and whether the last read ECG is problematic.
     *  
     * @param modality
     */
    public void setECGRecordingModality(RecordingModality modality);

    /**
     * Similar to the setECGRecordingModality(RecordingModality modality) method, but
     * passing the fields of the RecordingModality object as parameters. 
     * 
     * @param howManyTime
     * @param perWhatPeriod
     * @param howLong
     */
    public void setRecordingModality(int howManyTimes, Duration perWhatPeriod, Duration howLong);

    /**
     * returns the current recording modality for ECGs.
     * 
     * @return
     */
    public RecordingModality getECGRecordingModality();
    
    /**
     * This method will return the business rule component binding 
     * This is a record that contains the information needed to invoke
     * the Business Rules component for the HeartRPMABackEnd.
     * @return
     */
    public BRComponentBinding getBRComponentBinding();

    /**
     * This method will return the machine learning component binding 
     * This is a record that contains the information needed to invoke
     * the Machine Learning component for the HeartRPMABackEnd.
     * @return
     */
    public MLComponentBinding getMLComponentBinding();

    /**
     * This method will set the business rule component binding 
     * for the HeartRPMABackEnd.
     * @param brComponentBinding
     */
    public void setBRComponentBinding(BRComponentBinding brComponentBinding);

    /**
     * This method will set the machine learning component binding 
     * for the HeartRPMABackEnd.
     * @param mlComponentBinding
     */
    public void setMLComponentBinding(MLComponentBinding mlComponentBinding);

    /**
     * This method will connect to the Azure IoT Hub using the connection string and protocol specified in the IoTHubBinding record.
     * @throws IOException
     * @throws URISyntaxException
     */
    public void connectToAzureIoTHub() throws IOException, URISyntaxException;

    /**
     * This method will send a message to the Azure IoT Hub using the specified Message object. 
     * The message will be sent using the connection string and protocol specified in the IoTHubBinding record.
     * @param message
     * @throws IOException
     * @throws URISyntaxException
     */
    public void sendMessageToAzureIoTHub(Message message) throws IOException, URISyntaxException;

    /**
     * This method will set the IoTHubBinding for the HeartRPMABackEnd. 
     * The IoTHubBinding contains the connection string and protocol needed to connect to the Azure IoT Hub.
     * @param iotHubBinding
     */
    public void setIoTHubBinding(IoTHubBinding iotHubBinding);

    /**
     * This method will return the IoTHubBinding for the HeartRPMABackEnd.
     * @return
     */
    public IoTHubBinding getIoTHubBinding();

    /**
     * This method will send a message to the HeartMonitoringDevice using the specified HeartMonitoringDeviceMessage object.
     * The HeartMonitoringDeviceMessage contains the data to be sent to the device. In our case, it will be a message to the 
     * device to change its recording modality. We could add other messages to the device, but for now we will keep it simple.
     * @param device
     * @param message
     * @throws IOException
     * @throws URISyntaxException
     */
    public void sendMessageToHeartMonitoringDevice(HeartMonitoringDevice device, HeartMonitoringDeviceMessage message) throws IOException, URISyntaxException;
}
