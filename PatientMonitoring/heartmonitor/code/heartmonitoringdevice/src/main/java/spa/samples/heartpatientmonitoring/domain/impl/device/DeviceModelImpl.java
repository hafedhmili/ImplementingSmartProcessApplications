package spa.samples.heartpatientmonitoring.domain.impl.device;

import java.util.function.BooleanSupplier;

import spa.samples.heartpatientmonitoring.domain.types.device.DeviceFamily;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceModel;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class DeviceModelImpl implements DeviceModel {

    private DeviceFamily deviceFamily;
    private String modelName;
    private String manufacturer;
    private String modelDescription;
    private java.util.Collection<ECGFormat> ecgFormats;

    /**
     * a convenience constructor to create a device model with all the attributes set.
     * @param deviceFamily
     * @param modelName
     * @param manufacturer
     * @param modelDescription
     */
    public DeviceModelImpl(DeviceFamily deviceFamily, String modelName, String manufacturer, String modelDescription) {
        this(deviceFamily, modelName, manufacturer);
        this.modelDescription = modelDescription;
    }

    /**
     * a constructor to create a device model with the mandatory attributes set.
     * @param deviceFamily
     * @param modelName
     * @param manufacturer
     */
    public DeviceModelImpl(DeviceFamily deviceFamily, String modelName, String manufacturer) {
        this.deviceFamily = deviceFamily;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        ecgFormats = new java.util.ArrayList<>();
    }
    @Override
    public DeviceFamily getDeviceFamily() {
        return deviceFamily;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getModelDescription() {
        return modelDescription;

    }

    @Override
    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    @Override
    public java.util.Iterator<ECGFormat> getECGFormats() {
        return ecgFormats.iterator();
    }

    @Override
    public void addECGFormat(ECGFormat ecgFormat) {
        ecgFormats.add(ecgFormat);
        
    }

    @Override
    public ECGFormat removeECGFormat(ECGFormat ecgFormat) {
        boolean found = ecgFormats.remove(ecgFormat);
        return found ? ecgFormat : null;
    }

    @Override
    public boolean supportsFormat(ECGFormat ecgFormat) {
        return ecgFormats.contains(ecgFormat);
    }
    
}
