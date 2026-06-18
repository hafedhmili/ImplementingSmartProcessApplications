package spa.samples.heartpatientmonitoring.domain.impl.device.driver;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.domain.types.device.driver.HeartMonitoringDeviceDriver;

public class HeartMonitoringDeviceDriverImpl implements HeartMonitoringDeviceDriver {

    private HeartMonitorDevice heartMonitorDevice;

    private DeviceClient deviceClient;

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

    @Override
    public void startMonitoring() {
        RecordingModality modality = heartMonitorDevice.getRecordingModality();
        while (true) {
            break;
        }
        return;
    }

    @Override
    public void stopMonitoring() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopMonitoring'");
    }

    @Override
    public String getIotHubConnectionString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIotHubConnectionString'");
    }

    @Override
    public void createDeviceClientWith(String iotHubConnectionString, IotHubClientProtocol iotHubConnectionProtocol) {
        this.deviceClient = new DeviceClient(iotHubConnectionString, iotHubConnectionProtocol);
    }
}