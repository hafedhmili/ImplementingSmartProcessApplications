package spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.impl.util.MeasurementImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFormat;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGraw;
import spa.samples.heartpatientmonitoring.domain.types.util.MeasurementType;

public class ECGRawImpl extends MeasurementImpl implements ECGraw {

    private String deviceID;
    private ECGFile rawData;
    private ECGFormat format;

    public ECGRawImpl(String deviceID, Instant startTime, Instant endTime) {
        super(MeasurementType.ElectroCardiogram, startTime, endTime);
        this.deviceID = deviceID;
    }

    public ECGRawImpl(String deviceID,  Instant startTime, Instant endTime, ECGFile rawData, ECGFormat format) {
        this (deviceID, startTime, endTime);
        this.setRawData(rawData, format);
    }

    @Override
    public String getDeviceID() {
        return deviceID;
    }

    @Override
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public ECGFile getRawData() {
        return rawData;
    }

    @Override
    public void setRawData(ECGFile rawDataFile, ECGFormat format) {
        this.rawData = rawDataFile;
        this.format = format;
    }

    @Override
    public ECGFormat getFormat() {
        return format;
    }
    
}
