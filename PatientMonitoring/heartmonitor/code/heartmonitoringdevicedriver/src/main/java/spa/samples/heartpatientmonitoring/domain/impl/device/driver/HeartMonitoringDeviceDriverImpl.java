package spa.samples.heartpatientmonitoring.domain.impl.device.driver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.FileUploadCompletionNotification;
import com.microsoft.azure.sdk.iot.device.FileUploadSasUriRequest;
import com.microsoft.azure.sdk.iot.device.FileUploadSasUriResponse;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;

import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.domain.types.device.driver.HeartMonitoringDeviceDriver;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGraw;

public class HeartMonitoringDeviceDriverImpl implements HeartMonitoringDeviceDriver {

    private HeartMonitorDevice heartMonitorDevice;

    private DeviceClient deviceClient;

    private IotHubClientProtocol iotHubClientProtocol = null;

    private String iotHubConnectionString;

    /**
     * This task is used to simulate the monitoring process of the heart monitoring device. 
     * It will run in a separate thread and will be responsible for:
     * 1) recording ECGs based on HeartMonitorDevice RecordingModality and
     * 2) upload the recorded ECGs, as a file, to the IoT Hub. 
     * The task will run until the stopMonitoring() method is called. It will also update its parameters 
     * based on the changes made to the HeartMonitorDevice RecordingModality.
     */
    private CompletableFuture<Void> monitoringTask;

    private boolean monitoringStatus;

    public HeartMonitoringDeviceDriverImpl(HeartMonitorDevice heartMonitorDevice, IotHubClientProtocol protocol, String iotHubConnectionString) {
        this.heartMonitorDevice = heartMonitorDevice;
        this.iotHubClientProtocol = protocol;
        this.iotHubConnectionString = iotHubConnectionString;
        monitoringStatus = false;
    }

    @Override
    public HeartMonitorDevice getHeartMonitorDevice() {
        return heartMonitorDevice;
    }

    @Override
    public DeviceClient getHeartMonitorDeviceClient() {
        return deviceClient;
    }

    @Override
    public void setRecordingModality(RecordingModality modality) {
        heartMonitorDevice.setRecordingModality(modality);
    }

    @Override
    public String getDeviceID() {
        return heartMonitorDevice.getDeviceID();
    }

    @Override
    public String getPatientID() {
        return heartMonitorDevice.getPatientID();
    }

    /**
     * This is the function that runs continuously to record ECGs of certain length (<code>durationECG</code>)
     * in format <code>format</code> every <code>timeBetween ECG</code>.
     * 
     * This function is launched into its own thread by the function startMonitoring, as a CompletableFuture.
     * 
     * @param durationECG
     * @param format
     * @param timeBetweenECG
     */
    private void recordECGsOfDurationAndFormatEvery(Duration durationECG, ECGFormat format, Duration timeBetweenECG) {
        while (monitoringStatus) {
            try {
                Thread.sleep(timeBetweenECG.toMillis());
                ECGraw ecg= heartMonitorDevice.takeECG(durationECG);
                this.uploadFile(ecg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                System.out.println("For some reason, I cannot access raw ECG file");
                ioe.printStackTrace();
            } catch (IotHubClientException ioTHCE) {
                System.out.println("For some reason, the device client cannot connect to IoT Hub");
                ioTHCE.printStackTrace();
            }
        }
    }

    /**
     * This is the utility function called inside the monitoring loop that will upload the captured ECGs once they are captured
     * @param ecg
     * @throws IOException
     * @throws IotHubClientException
     */
    private void uploadFile(ECGraw ecg) throws IOException, IotHubClientException {
        ECGFile ecgFile = ecg.getRawData();
        File file = new File(ecgFile.getFilePath(), ecgFile.getFileName());
        // get parameters for uploading the ECG file to the IoT Hub
        // The blob name on IoT hub will be deviceID_patientID_ECGstartTime
        String blobName = heartMonitorDevice.getDeviceID()+ "_"+ heartMonitorDevice.getPatientID() + "_" + ecg.getStartTime();
        FileUploadSasUriRequest request = new FileUploadSasUriRequest(blobName);
        FileUploadSasUriResponse sasUriResponse = deviceClient.getFileUploadSasUri(request);

        try
        {
            
            BlobClient blobClient = new BlobClientBuilder()
                        .endpoint(sasUriResponse.getBlobUri().toString())
                        .buildClient();

            blobClient.uploadFromFile(file.getPath());

            FileUploadCompletionNotification completionNotification = new FileUploadCompletionNotification(sasUriResponse.getCorrelationId(), true);
            deviceClient.completeFileUpload(completionNotification);

            System.out.println("Finished file upload for file " + blobName + "(" + ecgFile.getFilePath()+ecgFile.getFileName()+")");

        }
        catch (Exception e)
        {
            // Note that this is done even when the file upload fails. IoT Hub has a fixed number of SAS URIs allowed active
            // at any given time. Once you are done with the file upload, you should free your SAS URI so that other
            // SAS URIs can be generated. If a SAS URI is not freed through this API, then it will free itself eventually
            // based on how long SAS URIs are configured to live on your IoT Hub.
            FileUploadCompletionNotification completionNotification = new FileUploadCompletionNotification(sasUriResponse.getCorrelationId(), false);
            deviceClient.completeFileUpload(completionNotification);
            e.printStackTrace();
            return;
        }
        finally
        {
            deviceClient.close();
        }
    }

    @Override
    public void startMonitoring() {
        heartMonitorDevice.turnOn();
        monitoringStatus = true;
   
        RecordingModality modality = heartMonitorDevice.getRecordingModality();
        float betweenRecordings = modality.referencePeriod().toMillis()/modality.frequency();
        Duration timeBetweenECG = Duration.ofMillis((long)betweenRecordings);
        ECGFormat format = heartMonitorDevice.getCurrentECGFormat();
        monitoringTask = CompletableFuture.supplyAsync(() -> {recordECGsOfDurationAndFormatEvery(modality.duration(), format, timeBetweenECG);return null;} );

        return;
    }

    @Override
    public void stopMonitoring() {
        monitoringTask.cancel(false);
        this.monitoringStatus = false;
        return;
    }

    @Override
    public String getIotHubConnectionString() {
        return iotHubConnectionString;
    }

    @Override
    public IotHubClientProtocol getIotHubClientProtocol() {
        return iotHubClientProtocol;
    }

    @Override
    public void setIotHubClientProtocol(IotHubClientProtocol protocol) {
        this.iotHubClientProtocol = protocol;
    }

    @Override
    public String getIoTHubDeviceId() throws IotHubClientException {
        return deviceClient.toString();
    }

    @Override
    public void connectToBackEnd() throws IotHubClientException {
        // 1. First, create a device client
        this.deviceClient = new DeviceClient(iotHubConnectionString, iotHubClientProtocol);

        // 2. Second, do a heart beat
        try {
                this.deviceClient.open(true);
                this.deviceClient.sendEvent(new Message("Hello from heart monitor of patient : " + heartMonitorDevice.getPatientID()));
            } catch (IllegalStateException | InterruptedException e) {
                e.printStackTrace();
            }
        
    }
}