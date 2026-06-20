package spa.samples.heartpatientmonitoring.domain.types.device.driver;

import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;

public interface HeartMonitoringDeviceDriver {

    public HeartMonitorDevice getHeartMonitorDevice();

    public DeviceClient getHeartMonitorDeviceClient();

    public void setRecordingModality(RecordingModality modality);

    public String getDeviceID();

    public String getPatientID();

    public void startMonitoring() throws IotHubClientException;

    public void stopMonitoring();

    public String getIotHubConnectionString();

    public IotHubClientProtocol getIotHubClientProtocol();

    public void setIotHubConnectionString(String connectionString);

    public void setIotHubClientProtocol( IotHubClientProtocol protocol);

    public void createDeviceClientWith() throws IotHubClientException;

}