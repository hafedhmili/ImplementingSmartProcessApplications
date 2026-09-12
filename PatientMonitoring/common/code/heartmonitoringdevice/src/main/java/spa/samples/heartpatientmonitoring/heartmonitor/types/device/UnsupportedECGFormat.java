package spa.samples.heartpatientmonitoring.heartmonitor.types.device;

import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFormat;

public class UnsupportedECGFormat extends Exception {

    private HeartMonitorDevice device;

    private ECGFormat ecgFormat;

    public UnsupportedECGFormat(HeartMonitorDevice device, ECGFormat format) {
        super("The Heart monitor device "+device + " of model "+device.getDeviceModel().getModelName() + " doesn't support the ECG format " + format);
        this.device = device;
        this.ecgFormat = format;
    }
    
    public HeartMonitorDevice getDevice() { return device;}

    public ECGFormat getUnsupportedFormat() {return ecgFormat;}
}
