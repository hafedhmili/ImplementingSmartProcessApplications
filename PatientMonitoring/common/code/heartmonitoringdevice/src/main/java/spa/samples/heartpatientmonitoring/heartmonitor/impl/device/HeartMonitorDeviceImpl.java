package spa.samples.heartpatientmonitoring.heartmonitor.impl.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.UUID;

import spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg.ECGFileImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg.ECGRawImpl;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.HeartMonitorDevice;
import spa.samples.heartpatientmonitoring.domain.types.device.RecordingModality;
import spa.samples.heartpatientmonitoring.heartmonitor.types.device.UnsupportedECGFormat;
import spa.samples.heartpatientmonitoring.domain.impl.device.MeasurementDeviceImpl;
import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;
import spa.samples.heartpatientmonitoring.domain.types.util.MeasurementType;

public class HeartMonitorDeviceImpl extends MeasurementDeviceImpl implements HeartMonitorDevice {

    public HeartMonitorDeviceImpl(String deviceID) {
        super(deviceID);
    }

    public HeartMonitorDeviceImpl(DeviceModel deviceModel) {
        super(deviceModel);
    }

    public HeartMonitorDeviceImpl(DeviceModel deviceModel, String patientID) {
        super(deviceModel, patientID);
    }

    /**
     * the current ECG recording format
     */
    private ECGFormat currentECGFormat;


    /*
     * the recording modality of the heart monitor device.
     */
    private RecordingModality recordingModality;

 
    @Override
    public RecordingModality getRecordingModality() {
        return recordingModality;
    }

    @Override
    public void setRecordingModality(RecordingModality recordingModality) {
        this.recordingModality = recordingModality;        
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

    @Override
    public ECGraw takeECG(Duration duration) {
        // just calls the inhertited takeMeasurement method, but returns an ECGraw object instead of a Measurement object.
       return (ECGraw) takeMeasurement(duration);
    }

    public  Measurement getMeasurementForType(String patientID, MeasurementType measurementType, Instant startTime, Duration duration) {
        if (measurementType == MeasurementType.ElectroCardiogram) {
            ECGFile rawData = getECGFileForRecording(patientID,currentECGFormat, startTime, duration);
            ECGraw ecg = new ECGRawImpl(this.getDeviceID(), startTime, startTime.plus(duration), rawData, currentECGFormat);

            // normally, the recording should have taken the specified duration, but here we are simulating the data
            // recording, and so we wait for the remaining time to elapse to mimic the end of the recording.
            Instant nowTime = Instant.now();

            Duration timeToWait = Duration.between(nowTime, startTime.plus(duration));

            if (!timeToWait.isNegative()) {
                try {
                    Thread.sleep(timeToWait.toMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            return ecg;
        } else {
            // For other measurement types, you can implement similar logic to create the appropriate Measurement object.
            // For now, we will return null for unsupported measurement types.
            return null;
        }
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

    public MeasurementType getMeasurementType() {
        return MeasurementType.ElectroCardiogram;
    }

}
