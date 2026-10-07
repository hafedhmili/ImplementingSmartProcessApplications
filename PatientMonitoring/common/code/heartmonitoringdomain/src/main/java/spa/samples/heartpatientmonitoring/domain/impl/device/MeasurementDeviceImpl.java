package spa.samples.heartpatientmonitoring.domain.impl.device;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.UUID;

import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceState;
import spa.samples.heartpatientmonitoring.domain.types.device.MeasurementDevice;
import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;
import spa.samples.heartpatientmonitoring.domain.types.util.MeasurementType;

public abstract class MeasurementDeviceImpl implements MeasurementDevice {

        /**
     * the device model of the heart monitor device. It is a mandatory attribute and is set in the constructor.
     */
    private DeviceModel deviceModel = null;

    /*
     * the unique identifier of the heart monitor device.
     */
    private String deviceID = null;

    /**
     * the patient to whom the heart monitor device is attached. It is set by the method <code>setPatientID(String patientID)</code>.
     */
    private String patientID = null;

    /*
     * the state of the heart monitor device.
     */
    private DeviceState deviceState = DeviceState.Off;

       /*
     * the latest ECG reading from the heart monitor device.
     */
    private Measurement latestMeasurement = null;
    
    /**
     * the current location of the heart monitor device.
     */
    private Location currentLocation = null;

    /**
     * the latest start recording time of the heart monitor device.
     */
    private Instant latestStartRecordingTime = null;

       /**
     * the latest end recording time of the heart monitor device.
     */
    private Instant latestEndRecordingTime = null;

    /**
     * the ECG readings recorded by the heart monitor device. It is a collection of ECGraw objects.
     */
    private java.util.Collection<Measurement> measurements;

    public MeasurementDeviceImpl(String ID) {
        this.deviceID = ID;
        this.measurements = new java.util.ArrayList<>();
    }

    private static String generateId() {return UUID.randomUUID().toString();}

    public MeasurementDeviceImpl(DeviceModel deviceModel) {
        this(generateId());
        this.deviceModel = deviceModel;
    }

    public MeasurementDeviceImpl(DeviceModel deviceModel, String patientID) {
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
    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
        
    }

    @Override
    public Measurement getLatestMeasurement() {
        return latestMeasurement;
    }

    @Override
    public void setLatestMeasurement(Measurement measurement) {
        this.latestMeasurement = measurement;
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
    public Iterator<Measurement> getMeasurements() {
        return measurements.iterator();
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }

    @Override
    public Measurement removeMeasurement(Measurement measurement) {
        boolean found = this.measurements.remove(measurement);
        return found ? measurement : null;
    }

    @Override
    public Measurement takeMeasurement(Duration duration) {
        // first, check if I am attached to a patient
        if (this.getPatientID() == null || this.getPatientID().isEmpty()) {
            System.out.println("Device " + this.deviceID + " not attached to patient. Cannot take ECG!");
            return (Measurement)null;
        }

        // Device is attached to patient. We are good!
        Instant startTime = Instant.now();
        this.setLatestStartRecordingTime(startTime);
        this.setDeviceState(DeviceState.Recording);

        // here we get the measuremnent for the specified duration, and then add it to the list of measurements and mark it as the latest measurement.
        Measurement measurement = getMeasurementForType(this.getPatientID(), this.getMeasurementType(), startTime, duration);

        this.addMeasurement(measurement);
        this.setLatestMeasurement(measurement);
        this.setLatestEndRecordingTime(Instant.now());
        this.setDeviceState(DeviceState.Sleeping);
        return measurement;
    }

    public abstract Measurement getMeasurementForType(String patientID, MeasurementType measurementType, Instant startTime, Duration duration);


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

}
