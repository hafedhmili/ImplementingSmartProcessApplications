package spa.samples.heartpatientmonitoring.domain.device;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

import spa.samples.heartpatientmonitoring.domain.impl.device.DeviceFamilyImpl;
import spa.samples.heartpatientmonitoring.domain.impl.device.DeviceModelImpl;
import spa.samples.heartpatientmonitoring.domain.impl.device.HeartMonitorDeviceImpl;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceFamily;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceType;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class TestHeartMonitoringDevice {

    private static DeviceFamily HEART_MONITORING_FAMILY= null;

    private static DeviceFamily PORTABLE_ECG_MONITOR = null;

    private static DeviceModel WHOOP_50MG_ACTIVITY_TRACKER = null;

    private static HeartMonitorDevice  MY_WHOOP_50MG_DEVICE = null;

    @BeforeAll
    public static void initializeFamiliesAndModels() {
        HEART_MONITORING_FAMILY = new DeviceFamilyImpl(DeviceType.HeartMonitor, null, "Heart Monitoring Devices");
        PORTABLE_ECG_MONITOR = new DeviceFamilyImpl(DeviceType.ElectroCardiograph, HEART_MONITORING_FAMILY, "Portable ECG Monitor");
        WHOOP_50MG_ACTIVITY_TRACKER = new DeviceModelImpl(PORTABLE_ECG_MONITOR, "WHOOP 5.0/MG Activity Tracker", "www.whoop.com");
                WHOOP_50MG_ACTIVITY_TRACKER.addECGFormat(ECGFormat.CSV);
        WHOOP_50MG_ACTIVITY_TRACKER.addECGFormat(ECGFormat.HL7_XML);
        WHOOP_50MG_ACTIVITY_TRACKER.addECGFormat(ECGFormat.PDF_ECG);
        WHOOP_50MG_ACTIVITY_TRACKER.addECGFormat(ECGFormat.MAT);

    }
    @Test
    public void testDeviceFamilyCreation() {

        DeviceFamily heartMonitoringFamily = new DeviceFamilyImpl(DeviceType.HeartMonitor, null, "Heart Monitoring Devices");

        DeviceFamily portableECGMonitor = new DeviceFamilyImpl(DeviceType.ElectroCardiograph, heartMonitoringFamily, "Portable ECG Monitor");

        assertEquals(heartMonitoringFamily,portableECGMonitor.getSuperFamily(),"Portable ECG Monitor should have superfamily Heart Monitoring Devices");
        assertTrue(heartMonitoringFamily.includesSubfamily(portableECGMonitor), "The Heart Monitoring Device Family is supposed to have Portable ECG Monitor as subfamily");
    }
    
    @Test
    public void testDeviceModelCreation() {

        DeviceModel whoop50MG = new DeviceModelImpl(PORTABLE_ECG_MONITOR, "WHOOP 5.0/MG Activity Tracker", "www.whoop.com");

        assertEquals(PORTABLE_ECG_MONITOR, whoop50MG.getDeviceFamily(),"Supposed to have device family "+ PORTABLE_ECG_MONITOR.getFamilyName());

        whoop50MG.addECGFormat(ECGFormat.CSV);
        whoop50MG.addECGFormat(ECGFormat.HL7_XML);
        whoop50MG.addECGFormat(ECGFormat.PDF_ECG);
        whoop50MG.addECGFormat(ECGFormat.MAT);

        assertTrue(whoop50MG.supportsFormat(ECGFormat.CSV), "Model " + whoop50MG.getModelName()+ " is supposed to support ECG format " + ECGFormat.CSV);

    }

    @Test
    public void testHeartMonitorDeviceCreation() {
        HeartMonitorDevice myWhoop50MGDevice = new HeartMonitorDeviceImpl(WHOOP_50MG_ACTIVITY_TRACKER);

        assertEquals(DeviceState.Off, myWhoop50MGDevice.getDeviceState(),"Device supposed to be off");

        assertEquals(WHOOP_50MG_ACTIVITY_TRACKER, myWhoop50MGDevice.getDeviceModel(),"Supposed to have model " + WHOOP_50MG_ACTIVITY_TRACKER.getModelName());
    }

}
