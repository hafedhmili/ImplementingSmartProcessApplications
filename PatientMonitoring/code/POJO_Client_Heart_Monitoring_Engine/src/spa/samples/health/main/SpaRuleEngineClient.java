package spa.samples.health.main;

import com.ibm.rules.engine.load.EngineLoader;
import com.ibm.rules.engine.ruleflow.runtime.RuleflowEngine;
import com.ibm.rules.engine.ruleflow.runtime.RuleflowEngineInput;
import com.ibm.rules.engine.ruleflow.runtime.RuleflowEngineOutput;
import com.ibm.rules.engine.runtime.EngineDefinition;
import com.ibm.rules.engine.util.EngineExecutionException;
import com.ibm.rules.engine.util.EngineInvalidStateException;

import spa.samples.health.domain.Alert;
import spa.samples.health.domain.Device;
import spa.samples.health.domain.DeviceModel;
import spa.samples.health.domain.DeviceState;
import spa.samples.health.domain.DeviceType;
import spa.samples.health.domain.Patient;
import spa.samples.health.domain.Reading;
import spa.samples.health.domain.ReadingType;
import spa.samples.health.domain.ReadingValue;
import spa.samples.health.domain.ValueUnit;
import spa.samples.health.util.AbstractFactory;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaRuleEngineClient {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("RuleEngine testing");
		String PATIENT_PARAM = "heart_patient";
		String ALERT_PARAM = "heart_alert";
		String MONITOR_PARAM = "heart_monitor";
		String READING_PARAM = "heart_reading";
		
		AbstractFactory objectFactory = AbstractFactory.getSingleton();
		
		// 1. Load the ruleset archive into a RuleEngineDefinition
		File rulesetArchive = new File(args[0]);
		EngineLoader loader = new EngineLoader(rulesetArchive);
		EngineDefinition def = loader.load();


		// 2. Create a rule engine, and get a handle on its input parameters
		RuleflowEngine engine = (RuleflowEngine) def.createEngine();
		RuleflowEngineInput ruleflowEngineInput = engine.createRuleflowEngineInput();
		
		// 3. create and set ruleset parameters
		// 3.a 	Create patient, and set heart_patient parameter
		Instant birthday = Instant.now().minus(72*365 + 120,ChronoUnit.DAYS);
		Patient patient = objectFactory.createPatient("John", "Smith",birthday);
		ruleflowEngineInput.setParameter(PATIENT_PARAM, patient);
		
		// 3.b	Create heart monitor and attach it to patient
		DeviceType heartMonitorType = objectFactory.createDeviceType("Heart Monitor", "Monitors pulse");
		DeviceModel heartMonitorModel = objectFactory.createDeviceModel(heartMonitorType,"Heart Monitor Model5","Johnson & Johnson", "Monitors pulse");
		Device heartMonitor = objectFactory.createDevice(heartMonitorModel);
		heartMonitor.attachToPatient(patient);
		heartMonitor.setDeviceState(DeviceState.LowCharge);

		ruleflowEngineInput.setParameter(MONITOR_PARAM, heartMonitor);
		
		// 3.c	Create heart monitor reading and set corresponding parameter
		ReadingValue heartBeat = objectFactory.createReadingValue(ValueUnit.pulses_per_minute,182);
		Reading heartReading = objectFactory.createReading(patient, ReadingType.HeartBeat, heartBeat, heartMonitor);

		ruleflowEngineInput.setParameter(READING_PARAM, heartReading);
		
		
		// 4.	Execute the ruleflow and collect the output parameters + execution statistics
		RuleflowEngineOutput ruleflowEngineOutput;
		try {
			ruleflowEngineOutput = engine.execute(ruleflowEngineInput);
			int executedRuleInstanceCount = ruleflowEngineOutput.getExecutedRuleInstanceCount();
			int executedTasksCount = ruleflowEngineOutput.getExecutedTasksCount();
			Alert raisedAlert = (Alert)ruleflowEngineInput.getData().get(ALERT_PARAM);
			Device heartMonitorAfter = (Device)ruleflowEngineInput.getData().get(MONITOR_PARAM);
			logger.log(Level.INFO, "The state of the heart monitor after invocation is: "+heartMonitorAfter.getDeviceState());
			logger.log(Level.INFO, "Executing the heart monitoring rules on " + patient + " with heart monitor " + heartMonitor + " and reading " + heartReading + " yields:\n");
			logger.log(Level.INFO, "\t" + executedRuleInstanceCount + " rule instances executed");
			logger.log(Level.INFO, "\t" + executedTasksCount + " rule tasks executed");
			if (raisedAlert!= null)  logger.log(Level.INFO, "\tProducing the Alert: " + raisedAlert);
		} catch (EngineExecutionException | EngineInvalidStateException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
