/**
 * 
 */
package spa.samples.health.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import spa.samples.health.domain.ActType;
import spa.samples.health.domain.Activity;
import spa.samples.health.domain.ActivityType;
import spa.samples.health.domain.Address;
import spa.samples.health.domain.Address.AddressType;
import spa.samples.health.domain.Address.StreetOrientation;
import spa.samples.health.domain.Address.StreetType;
import spa.samples.health.domain.Alert;
import spa.samples.health.domain.AlertType;
import spa.samples.health.domain.Country;
import spa.samples.health.domain.Device;
import spa.samples.health.domain.DeviceModel;
import spa.samples.health.domain.DeviceType;
import spa.samples.health.domain.HealthAct;
import spa.samples.health.domain.HealthCondition;
import spa.samples.health.domain.HealthConditionType;
import spa.samples.health.domain.HealthEpisode;
import spa.samples.health.domain.HealthEpisodeType;
import spa.samples.health.domain.Location;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.PatientState;
import spa.samples.health.domain.PatientStateType;
import spa.samples.health.domain.Reading;
import spa.samples.health.domain.ReadingType;
import spa.samples.health.domain.ReadingValue;
import spa.samples.health.domain.Severity;
import spa.samples.health.domain.ValueUnit;
import spa.samples.health.util.AbstractFactory;
import spa.samples.health.util.DurationUnit;

/**
 * Author: Hafedh Mili
 */
public class AbstractFactoryImpl implements AbstractFactory {
	
	private static AbstractFactory SINGLETON;
	
	private HashMap<String,ActType> actTypes;
	
	private HashMap<String,ActivityType> activityTypes;
	
	private HashMap<String,HealthEpisodeType> healthEpisodeTypes;
	
	private HashMap<String,HealthConditionType> healthConditionTypes;
	
	private HashMap<String,DeviceType> deviceTypes;
	
	public static AbstractFactory getSingleton() {
		if (SINGLETON==null) SINGLETON = new AbstractFactoryImpl();
		return SINGLETON;
	}
	
	private AbstractFactoryImpl() {
		actTypes = new HashMap<String,ActType>();
		activityTypes = new HashMap<String,ActivityType>();
		deviceTypes = new HashMap<String,DeviceType>();
		healthEpisodeTypes =  new HashMap<String,HealthEpisodeType>();
		healthConditionTypes = new HashMap<String,HealthConditionType>();
	}

	@Override
	public Activity createActivity(ActivityType activityType, Patient patient) {
		return new ActivityImpl(activityType,patient);
	}

	@Override
	public ActivityType createActivityType(String name, String description) {
		ActivityType activityType = new ActivityTypeImpl(name,description);
		activityTypes.put(name,activityType);
		return activityType;
	}

	@Override
	public ActType createActType(String name, String description) {
		ActType actType = new ActTypeImpl(name,description);
		actTypes.put(name,actType);
		return actType;
	}
	
	@Override
	public DeviceType createDeviceType(String name, String description) {
		DeviceType deviceType = new DeviceTypeImpl(name,description);
		deviceTypes.put(name,deviceType);
		return deviceType;
	}


	@Override
	public Address createAddress(AddressType adType, String doorNumber, String streetNumber, StreetType stType,
			String streetName, String city, String stateProvince, String postalCode, Country country,
			StreetOrientation... orientation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alert createAlert(AlertType alertType, Patient patient, Reading reading) {
		Alert newAlert = new AlertImpl(alertType,patient,reading);
		return newAlert;
	}

	@Override
	public Device createDevice(DeviceModel deviceModel) {
		Device newDevice = new DeviceImpl(deviceModel);
		return newDevice;
	}

	@Override
	public DeviceModel createDeviceModel(DeviceType deviceType, String manufacturer,
			String modelName, String modelDescription) {
		DeviceModel newDeviceModel = new DeviceModelImpl(deviceType, manufacturer, modelName, modelDescription);
		return newDeviceModel;
	}

	@Override
	public Duration createDuration(DurationUnit unit, float value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration createDuration(Instant start, Instant end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthAct createHealthAct(ActType actType, String performedBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthEpisode createHealthEpisode(Instant startTime, Instant endTime, Severity severity,
			HealthEpisodeType episodeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthCondition createHealthCondition(Patient patient, HealthConditionType conditionType, Severity severity,
			HealthEpisode onset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthConditionType createHealthConditionType(String name, String description) {
		HealthConditionType hConditionType = new HealthConditionTypeImpl(name,description);
		healthConditionTypes.put(name,hConditionType);
		return hConditionType;
	}

	@Override
	public Location createLocation(float latitude, float longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient createPatient(String firstName, String lastName, Instant birthDate) {
		Patient newPatient = new PatientImpl(firstName,lastName,birthDate);
		return newPatient;
	}

	@Override
	public PatientState createPatientState(Patient patient, PatientStateType stateLabel, Instant startTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reading createReading(Patient patient, ReadingType type, ReadingValue value, Device device, Instant ...time) {
		return new ReadingImpl(patient,type,value, device,time);
	}

	@Override
	public ReadingValue createReadingValue(ValueUnit unit, Object value) {
		return new ReadingValueImpl(value, unit);
	}

	@Override
	public ActivityType getActivityTypeByName(String name) {
		return this.activityTypes.get(name);
	}

	@Override
	public ActType getActTypeByName(String name) {
		return this.actTypes.get(name);
	}

	@Override
	public DeviceType getDeviceTypeByName(String name) {
		return this.deviceTypes.get(name);
	}

	@Override
	public HealthConditionType getHealthConditionTypeByName(String name) {
		return this.healthConditionTypes.get(name);
	}

	@Override
	public HealthEpisodeType createHealthEpisodeType(String name, String description) {
		HealthEpisodeType newHealthEpisodeType = new HealthEpisodeTypeImpl(name,description);
		this.healthEpisodeTypes.put(name, newHealthEpisodeType);
		return newHealthEpisodeType;
	}

}
