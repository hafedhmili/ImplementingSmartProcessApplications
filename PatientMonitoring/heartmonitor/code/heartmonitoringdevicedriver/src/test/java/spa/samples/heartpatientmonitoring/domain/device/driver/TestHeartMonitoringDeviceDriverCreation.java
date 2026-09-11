package spa.samples.heartpatientmonitoring.domain.device.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;

import spa.samples.heartpatientmonitoring.domain.impl.device.DeviceFamilyImpl;
import spa.samples.heartpatientmonitoring.domain.impl.device.DeviceModelImpl;
import spa.samples.heartpatientmonitoring.domain.impl.device.HeartMonitorDeviceImpl;
import spa.samples.heartpatientmonitoring.domain.impl.device.driver.HeartMonitoringDeviceDriverImpl;
import spa.samples.heartpatientmonitoring.domain.impl.ecg.ECGFileImpl;
import spa.samples.heartpatientmonitoring.domain.impl.ecg.ECGRawImpl;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceFamily;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceType;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.driver.HeartMonitoringDeviceDriver;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGraw;

public class TestHeartMonitoringDeviceDriverCreation {
        
    
    public static IotHubClientProtocol DefaultIotHubClientProtocol = IotHubClientProtocol.MQTT;

    public static String IotHubIothubownerConnectionString = "HostName=IOT-hub-for-heart-monitors.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=8PzezT5i+ZCso2FYhrUJUJ7LzFKlHJPGnAIoTCGS3ms="; 

    public static String IotHubDevice04048ConnectionString = "HostName=IOT-hub-for-heart-monitors.azure-devices.net;DeviceId=heart-monitor-PID-04048;SharedAccessKey=n7TfVL8qeOXqvVoC+Zh9nEb79XM/CvQE7sYPeoAoJgk=";

    public static DeviceModel WHOOP_50MG_MODEL = null;

    @BeforeAll
    public static void creationHeartMonitoringDeviceModel() {

        // 1.   First, create, DeviceFamily'ies
        DeviceFamily heartMonitoringFamily = new DeviceFamilyImpl(DeviceType.HeartMonitor, null, "Heart Monitoring Devices");
        DeviceFamily portableECGMonitor = new DeviceFamilyImpl(DeviceType.ElectroCardiograph, heartMonitoringFamily, "Portable ECG Monitor");

        // 2.   Second, create a DeviceModel
        WHOOP_50MG_MODEL = new DeviceModelImpl(portableECGMonitor, "WHOOP 5.0/MG Activity Tracker", "www.whoop.com");

        assertEquals(portableECGMonitor, WHOOP_50MG_MODEL.getDeviceFamily(),"Supposed to have device family "+ portableECGMonitor.getFamilyName());

        WHOOP_50MG_MODEL.addECGFormat(ECGFormat.CSV);
        WHOOP_50MG_MODEL.addECGFormat(ECGFormat.HL7_XML);
        WHOOP_50MG_MODEL.addECGFormat(ECGFormat.PDF_ECG);
        WHOOP_50MG_MODEL.addECGFormat(ECGFormat.MAT);

        assertTrue(WHOOP_50MG_MODEL.supportsFormat(ECGFormat.CSV), "Model " + WHOOP_50MG_MODEL.getModelName()+ " is supposed to support ECG format " + ECGFormat.CSV);

    }

    @Test
    public void testCreationHeartMonitorDeviceDriver() {
        // 1. First create, a HeartMonitorDevice
        HeartMonitorDevice whoop50MGDevice =  new HeartMonitorDeviceImpl(WHOOP_50MG_MODEL);

        // 2.   Set its patient ID
        whoop50MGDevice.setPatientID("04048");

        // 3.   Create a devicer driver
        HeartMonitoringDeviceDriver heartMonitorDeviceDriver = new HeartMonitoringDeviceDriverImpl(whoop50MGDevice, DefaultIotHubClientProtocol, IotHubDevice04048ConnectionString);

        // 4.   Connect it to backend
        
        try {
            heartMonitorDeviceDriver.connectToBackEnd();
        } catch (IotHubClientException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void testUploadingECGFile() {
    
        // 1. First create, a HeartMonitorDevice
        HeartMonitorDevice whoop50MGDevice =  new HeartMonitorDeviceImpl(WHOOP_50MG_MODEL);

        // 2.   Set its patient ID
        whoop50MGDevice.setPatientID("04048");

        // 3.   Create a devicer driver
        HeartMonitoringDeviceDriver heartMonitorDeviceDriver = new HeartMonitoringDeviceDriverImpl(whoop50MGDevice, DefaultIotHubClientProtocol, IotHubDevice04048ConnectionString);

        // 4.   Connect it to backend
        
        try {
            heartMonitorDeviceDriver.connectToBackEnd();
        
            System.out.println("Device driver connected to backend, now uploading an ECG file");
            // 5.   Build an ECG file
            String patientID = whoop50MGDevice.getPatientID();
            Instant startTime = Instant.now();
            Duration duration = Duration.ofSeconds(300);
            ECGFile ecgFile = ECGFileImpl.getECGFileForRecording(patientID,ECGFormat.CSV,startTime,duration);
            ECGraw ecg = new ECGRawImpl(whoop50MGDevice.getDeviceID(), startTime, startTime.plus(duration), ecgFile, ECGFormat.CSV);

            heartMonitorDeviceDriver.uploadFile(ecg);       
        } catch (IotHubClientException e) {
            System.out.println("For some reason, the device client cannot connect to IoT Hub");
            System.err.println("IoT Hub Error Status: " + e.getStatusCode());
            System.err.println("IoT Hub Error Message: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason, I cannot access raw ECG file");
            e.printStackTrace();
        }
    }



}
