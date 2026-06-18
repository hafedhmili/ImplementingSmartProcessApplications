package spa.samples.heartpatientmonitoring.domain.impl.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.UUID;

import spa.samples.heartpatientmonitoring.domain.impl.ecg.ECGFileImpl;
import spa.samples.heartpatientmonitoring.domain.impl.ecg.ECGRawImpl;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.domain.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;

public class HeartMonitorDeviceImpl implements HeartMonitorDevice {

    /**
     * the device model of the heart monitor device. It is a mandatory attribute and is set in the constructor.
     */
    private DeviceModel deviceModel;

    /*
     * the unique identifier of the heart monitor device.
     */
    private String deviceID;

    /**
     * the patient to whom the heart monitor device is attached. It is set by the method <code>setPatientID(String patientID)</code>.
     */
    private String patientID;

    /*
     * the state of the heart monitor device.
     */
    private DeviceState deviceState;

    /*
     * the recording modality of the heart monitor device.
     */
    private RecordingModality recordingModality;

    /*
     * the latest ECG reading from the heart monitor device.
     */
    private ECGraw latestECG;
    
    /**
     * the current location of the heart monitor device.
     */
    private Location currentLocation;

    /**
     * the latest start recording time of the heart monitor device.
     */
    private Instant latestStartRecordingTime;

    /**
     * the ECG readings recorded by the heart monitor device. It is a collection of ECGraw objects.
     */
    private java.util.Collection<ECGraw> ecgs;

    private static String generateId() {return UUID.randomUUID().toString();}

    public HeartMonitorDeviceImpl(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
        this.deviceID = generateId();
        this.deviceState = DeviceState.Off;
        this.ecgs = new java.util.ArrayList<>();
    }

    public HeartMonitorDeviceImpl(DeviceModel deviceModel, String patientID) {
        this(deviceModel);
        this.patientID = patientID;
    }

    @Override
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    @Override
    public String getDeviceID() {
        return deviceID;
    }

    @Override
    public String getPatientID() {
        return patientID;
    }

    @Override
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public DeviceState getDeviceState() {
        return deviceState;
    }

    @Override
    public RecordingModality getRecordingModality() {
        return recordingModality;
    }

    @Override
    public void setRecordingModality(RecordingModality recordingModality) {
        this.recordingModality = recordingModality;        
    }

    @Override
    public void setDeviceState(DeviceState deviceState) {
        
    }

    @Override
    public ECGraw getLatestECG() {
        return latestECG;
    }

    @Override
    public void setLatestECG(ECGraw ecg) {
        this.latestECG = ecg;
    }

    @Override
    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;        
    }

    @Override
    public Instant getLatestStartRecordingTime() {
        return latestStartRecordingTime;
    }

    @Override
    public void setLatestStartRecordingTime(Instant startRecordingTime) {
        this.latestStartRecordingTime = startRecordingTime;
    }

    @Override
    public Iterator<ECGraw> getECGs() {
        return ecgs.iterator();
    }

    @Override
    public void addECG(ECGraw anECG) {
        this.ecgs.add(anECG);
    }

    @Override
    public ECGraw removeECG(ECGraw anECG) {
        boolean found = this.ecgs.remove(anECG);
        return found ? anECG : null;
    }

    @Override
    public ECGraw takeECG(ECGFormat format, Duration duration) {
        Instant startTime = Instant.now();
        ECGFile rawData = getECGFileForRecording(format, startTime, duration);
                                                                            
        ECGraw ecg = new ECGRawImpl(this.getDeviceID(), startTime, startTime.plus(duration), rawData, format);
        this.addECG(ecg);
        this.setLatestECG(ecg);
        return ecg;
    }

    /**
     * This is a helper method for the <code>takeECG(ECGFormat format, Duration duration)</code> method.
     * It gets the ECG file in format <code>format</code>, for recording ECG signals starting 
     * at <code>startTime</code> and for the specified <code>duration</code>.
     * 
     * For simulation purposes, this method can return an ECG file with the specified format and duration, from the ECG
     * data set. In a real implementation, this method would interact with the hardware of the heart monitor device to 
     * capture the ECG signals and store them in a file in the specified format.
     * 
     * @param format the format of the ECG
     * @param startTime the start time of the recording
     * @param duration the duration of the recording
     * @return the ECG file
     */
    private ECGFile getECGFileForRecording(ECGFormat format, Instant startTime, Duration duration) {
        return ECGFileImpl.getECGFileForRecording(format, startTime, duration);
    }
    
}
