package spa.samples.heartpatientmonitoring.heartmonitor.impl.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.UUID;

import spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg.ECGFileImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg.ECGRawImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.UnsupportedECGFormat;

public class HeartMonitorDeviceImpl implements HeartMonitorDevice {

    /**
     * the current ECG recording format
     */
    private ECGFormat currentECGFormat;

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
     * the latest end recording time of the heart monitor device.
     */
    private Instant latestEndRecordingTime;

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
    public ECGraw takeECG(Duration duration) {
        // first, check if I am attached to a patient
        if (this.getPatientID() == null || this.getPatientID().isEmpty()) {
            System.out.println("Device " + this.deviceID + " not attached to patient. Cannot take ECG!");
            return (ECGraw)null;
        }

        // Device is attached to patient. We are good!
        Instant startTime = Instant.now();
        this.setLatestStartRecordingTime(startTime);
        this.setDeviceState(DeviceState.Recording);
        // here we fake the recording of the ECG by getting an ECG file for the specified format and duration, 
        // and then creating an ECGraw object for it, and adding it to the list of ECGs and marking it as the latest ECG.
        ECGFile rawData = getECGFileForRecording(this.getPatientID(),currentECGFormat, startTime, duration);
                                                                            
        ECGraw ecg = new ECGRawImpl(this.getDeviceID(), startTime, startTime.plus(duration), rawData, currentECGFormat);

        // normally, the recording should have taken the specified duration, but in case it took less time, 
        // to get the corresponding file from the dataset, we wait for the remaining time to elapse 
        // before mimicking the end of the recording.
        Instant nowTime = Instant.now();

        Duration timeToWait = Duration.between(nowTime, startTime.plus(duration));

        if (!timeToWait.isNegative()) {
            try {
                Thread.sleep(timeToWait.toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.addECG(ecg);
        this.setLatestECG(ecg);
        this.setLatestEndRecordingTime(Instant.now());
        this.setDeviceState(DeviceState.Sleeping);
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
    private ECGFile getECGFileForRecording(String patientID, ECGFormat format, Instant startTime, Duration duration) {
        return ECGFileImpl.getECGFileForRecording(patientID,format, startTime, duration);
    }

    @Override
    public void turnOn() {
        this.setDeviceState(DeviceState.Sleeping);
    }

    @Override
    public void turnOff() {
        this.setDeviceState(DeviceState.Off);
    }

    @Override
    public Instant getLatestEndRecordingTime() {
        return latestEndRecordingTime;
    }

    @Override
    public void setLatestEndRecordingTime(Instant endRecordingTime) {
        this.latestEndRecordingTime = endRecordingTime;
    }

    @Override
    public ECGFormat getCurrentECGFormat() {
        return currentECGFormat;
    }

    @Override
    public void setCurrentECGFormat(ECGFormat format) throws UnsupportedECGFormat{
        if (!supportsECGFormat(format)) throw new UnsupportedECGFormat(this, format);
        this.currentECGFormat = format;
    }

    @Override
    public boolean supportsECGFormat(ECGFormat ecgFormat) {
        return getDeviceModel().supportsFormat(ecgFormat);
    }
}
