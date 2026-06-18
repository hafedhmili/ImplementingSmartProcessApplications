package spa.samples.heartpatientmonitoring.domain.types.device.driver;

import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;

public interface HeartMonitoringDeviceDriver {

    public HeartMonitorDevice getHeartMonitorDevice();

    public DeviceClient getHeartMonitorDeviceClient();

    public void setRecordingModality(RecordingModality modality);

    public String getDeviceID();

    public String getPatientID();

    public void startMonitoring();

    public void stopMonitoring();

    public String getIotHubConnectionString();

    public void createDeviceClientWith(String iotHubConnectionString, IotHubClientProtocol iotHubConnectionProtocol);

}