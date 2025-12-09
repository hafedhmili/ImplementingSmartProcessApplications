/**
 * 
 */
package spa.samples.health.util;

import java.time.Duration;
import java.time.Instant;

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
import spa.samples.health.impl.AbstractFactoryImpl;

/**
 * Author: Hafedh Mili
 */
public interface AbstractFactory {
	
	/**
	 * creates an activity of type <code>activityType</code> performed by <code>patient</code>
	 * @param activityType
	 * @param patient
	 * @return
	 */
	public Activity createActivity(ActivityType activityType, Patient patient);
	
	
	/**
	 * creates an activity type with <code>name</code> and <code>description</code>
	 * @param name
	 * @param description
	 * @return
	 */
	
	public ActivityType createActivityType(String name, String description);
	
	/**
	 * get an <code>ActivityType</code> by name, if one had been created
	 * @param name
	 * @return
	 */
	public ActivityType getActivityTypeByName(String name);
	
	/**
	 * Returns a medical act type with <code>name</code> and <code>description</code>
	 * @param name
	 * @param description
	 * @return
	 */
	public ActType createActType(String name, String description);
	
	/**
	 * the factory should allow us to retrieve ActType's by name
	 * @param name
	 * @return
	 */
	public ActType getActTypeByName(String name);
	
	/**
	 * Create an address with the provided parameters. The street orientation is optional.
	 * If a door number is missing, provide null.
	 * @param adType
	 * @param doorNumber
	 * @param streetNumber
	 * @param stType
	 * @param streetName
	 * @param city
	 * @param stateProvince
	 * @param postalCode
	 * @param country
	 * @param orientation
	 * @return
	 */
	public Address createAddress(AddressType adType, String doorNumber, String streetNumber, StreetType stType, String streetName, String city, String stateProvince, String postalCode, Country country, StreetOrientation ... orientation );
	

	/**
	 * create an alert of type <code>alertType</code> for the <code>patient</code>, given the <code>reading</code>
	 * @param alertType
	 * @param patient
	 * @param reading
	 * @return
	 */
	public Alert createAlert(AlertType alertType, Patient patient, Reading reading);
	
	/**
	 * create a device of model <code>deviceModel</code> at <code>location</code>
	 * @param deviceModel
	 * @return
	 */
	public Device createDevice(DeviceModel deviceModel);
	
	/**
	 * create a device model of type <code>deviceType</code>, produced by
	 * <code>manufacturer</code>, with name <code>modelName</code>, and
	 * description <code>modelDescription</code>
	 * @param deviceType
	 * @param manufacturer
	 * @param modelDescription
	 * @param modelName
	 * @return
	 */
	public DeviceModel createDeviceModel(DeviceType deviceType, String manufacturer, String modelDescription, String modelName);
	
	/**
	 * create a device type with code>name</code> and collection of <code>supportedReadingTypes</code>
	 * @param name
	 * @param description
	 * @return
	 */
	public DeviceType createDeviceType(String name, String description);
	
	/**
	 * returns <code>DeviceType</code> with <code>name</code>, if one exists/had been
	 * created.
	 * 
	 * @param name
	 * @return
	 */
	public DeviceType getDeviceTypeByName(String name);
	
	/**
	 * create a duration of <code>unit</code>, with <code>value</code>
	 * @param unit
	 * @param value
	 * @return
	 */
	public Duration createDuration(DurationUnit unit, float value);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public Duration createDuration(Instant start, Instant end);
	
	/**
	 * create a health act
	 * @param actType
	 * @param performedBy
	 * @return
	 */
	public HealthAct createHealthAct(ActType actType, String performedBy);
	
	/**
	 * Create a health episode starting at <code>startTime</code>, ending at <code>endTime</code>, of type 
	 * <code>episodeType</code>, <code>severity</code>
	 * @param startTime
	 * @param endTime
	 * @param severity
	 * @param episodeType
	 * @return
	 */
	public HealthEpisode createHealthEpisode(Instant startTime, Instant endTime, Severity severity, HealthEpisodeType episodeType);
	
	/**
	 * Create a <code>HealthEpisodeType</code> with given <code>name</code> and <code>description</code>
	 * @param name
	 * @param description
	 * @return
	 */
	public HealthEpisodeType createHealthEpisodeType(String name, String description);
	

	/**
	 * Create a health condition of type <code>conditionType</code> for <code>patient</code> with <code>severity</code>, with <code>onset</code>
	 * @param patient
	 * @param conditionType
	 * @param severity
	 * @param onset
	 * @return
	 */
	public HealthCondition createHealthCondition(Patient patient, HealthConditionType conditionType, Severity severity, HealthEpisode onset);
	
	/**
	 * creates a health condition type, given a <code>name</code> and a <code>description</code>. A health condition type is a 
	 * pathology of some sort (e.g. hypertension), and then there are instances of it, for specific patients (John's hypertension,
	 * Mary's hypertension)
	 * @param name
	 * @param description
	 * @return
	 */
	public HealthConditionType createHealthConditionType(String name, String description);
	
	/**
	 * get<code>HealthConditionType</code> by <code>name</code>, if one had been created
	 * with that name
	 * @param name
	 * @return
	 */
	public HealthConditionType getHealthConditionTypeByName(String name);
	
	/**
	 * creates a location given a latitude and a longitude
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public Location createLocation(float latitude, float longitude);
	
	/**
	 * creates a patient with given <code>firstName</code>, <code>lastName</code>, and 
	 * <code>birthDate</code>.
	 * 
	 * Every other attribute of <code>Patient</code> is modifiable after creation
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @return
	 */
	public Patient createPatient(String firstName, String lastName, Instant birthDate);
	
	/**
	 * Create a patient state for <code>patient</code>, with label <code>stateLabel</code>, 
	 * starting at <code>startTime</code>
	 * @param patient
	 * @param stateLabel
	 * @param startTime
	 * @return
	 */
	public PatientState createPatientState(Patient patient, PatientStateType stateLabel, Instant startTime);
	
	/**
	 * create a reading for <code>patient</code>, of type <code>readingType</code>, with <code>value</code>,
	 * from device <code>readingDevice</code>, at time <code>time</code>. If the time is not supplied, we
	 * use the current time
	 * @param patient
	 * @param type
	 * @param value
	 * @param device
	 * @param time
	 * @return
	 */
	public Reading createReading(Patient patient, ReadingType type, ReadingValue value, Device device, Instant ...time);

	/**
	 * create a reading value with <code>unit</code> and <code>value</code>
	 * @param unit
	 * @param value
	 * @return
	 */
	public ReadingValue createReadingValue(ValueUnit unit, Object value);
	

	public static AbstractFactory getSingleton() {
		return AbstractFactoryImpl.getSingleton();
	}
}

