package spa.samples.heartpatientmonitoring.domain.types.device.driver;

import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;

import java.io.IOException;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGraw;

public interface HeartMonitoringDeviceDriver {

    /**
     * Returns the software representation of the "physical" device 
     * that this object is "driving" (controlling)
     * @return
     */
    public HeartMonitorDevice getHeartMonitorDevice();

    /**
     * returns the "IoTHub representation" of this heart monitor,
     * which is a </code>DeviceClient</code> that acts as an intermediary
     * between the IotHub and the "physical device"
     * @return
     */
    public DeviceClient getHeartMonitorDeviceClient();

    /**
     * changes the <code>RecordingModality</code> of the
     * "physical device" that this 'driver' is controlling.
     * 
     * Typically, such a command would be triggered by the 
     * back-end, depending on the results of the analysis of the
     * latest ECG. Thus, the <code>DeviceClient</code> would
     * receive a command from IoT hub, and handling that
     * command will result into calling this method
     */
    public void setRecordingModality(RecordingModality modality);

    /**
     * returns the "physical" (HeartMonitorDevice) device ID
     * @return
     */
    public String getDeviceID();

    /**
     * this method returns the ID of the current heart monitoring device
     * as it is known to IoT Hub
     * @return
     */
    public String getIoTHubDeviceId() throws IotHubClientException;

    /**
     * return the ID of the patient being monitored. The ID can be found
     * in the HeartMonitorDevice
     * 
     * @return
     */
    public String getPatientID();

    /**
     * This method starts the "inifnite loop" of monitoring the patient,
     * by recording and uploading ECG according to the <code>RecordingModality</code>
     * of the physical device
     */
    public void startMonitoring() throws IotHubClientException;

    /**
     * This method stops the monitoring.
     */
    public void stopMonitoring();

    /**
     * returns the connection string used by the <code>DeviceClient</code>
     * to connect to IoT Hub
     * 
     * @return
     */
    public String getIotHubConnectionString();

    /**
     * Returns the client connection protocol
     * @return
     */
    public IotHubClientProtocol getIotHubClientProtocol();

    /**
     * we can change the connection protocol, after device driver
     * creation (but not the connection string)
     * @param protocol
     */
    public void setIotHubClientProtocol(IotHubClientProtocol protocol);


    /**
     * This method connects the current HeartMonitoringDeviceDriver to the
     * heart monitoring SPA back-end. Sort of having the heart monitor 
     * "come alive/online", ready to respond to requests to start recording
     * or to modify its parameter. 
     * 
     * Connecting to backend does not mean starting to record. In effect, it 
     * creates a DeviceClient that connects to IotHub with the connection
     * parameters, and sends a test message.
     */
    public void connectToBackEnd() throws IotHubClientException;


    /**
     * Public function to upload a file to the IoT Hub using Azure IoT functionality to upload files.
     * This function is called by the monitoring task, when it has recorded an ECG and wants to upload 
     * it to the IoT Hub.
     * @param ecg
     * @throws IOException
     * @throws IotHubClientException
     */
    public void uploadFile(ECGraw ecg) throws IOException, IotHubClientException ;

}