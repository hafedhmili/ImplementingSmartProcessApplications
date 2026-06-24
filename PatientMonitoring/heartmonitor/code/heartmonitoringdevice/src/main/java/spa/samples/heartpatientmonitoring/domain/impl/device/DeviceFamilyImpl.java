package spa.samples.heartpatientmonitoring.domain.impl.device;
import java.util.ArrayList;
import java.util.Collection;

import spa.samples.heartpatientmonitoring.domain.types.device.DeviceFamily;
import spa.samples.heartpatientmonitoring.domain.types.device.DeviceType;

public class DeviceFamilyImpl implements DeviceFamily {

    private DeviceType deviceType;

	private DeviceFamily superFamily = null;

	private Collection<DeviceFamily> subFamilies;

	private String familyName;

	private String familyDescription;

    public DeviceFamilyImpl(DeviceType deviceType, DeviceFamily superFamily, String familyName) {
        this.deviceType = deviceType;
        if (superFamily !=null) {
            this.superFamily = superFamily;
            superFamily.addSubfamily(this);
        }      
        this.familyName = familyName;
        subFamilies = new ArrayList<>();
       
    }
	

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public DeviceFamily getSuperFamily() {
        return superFamily;
    }

    @Override
    public String getFamilyName() {
        return familyName;
    }

    @Override
    public String getFamilyDescription() {
        return familyDescription;
    }

    @Override
    public void setFamilyDescription(String familyDescription) {
        this.familyDescription = familyDescription;
    }

    @Override
    public java.util.Iterator<DeviceFamily> getSubfamilies() {
        return subFamilies.iterator();
    }

    @Override
    public void addSubfamily(DeviceFamily subFamily) {
        subFamilies.add(subFamily);
        
    }

    @Override
    public DeviceFamily removeDeviceSubfamily(DeviceFamily deviceFamily) {
        boolean found = subFamilies.remove(deviceFamily);
        return found ? deviceFamily : null;
    }


    @Override
    public boolean includesSubfamily(DeviceFamily subFamily) {
        return subFamilies.contains(subFamily);
    }

    
}
