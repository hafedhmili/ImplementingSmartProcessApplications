package spa.samples.heartpatientmonitoring.heartmonitor.device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import spa.samples.heartpatientmonitoring.heartmonitor.impl.ecg.ECGFileImpl;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.heartmonitor.types.ecg.ECGFormat;

public class TestECGReadingAndSlicing {
    @Test
    public void testAcessingPatientECGFiles() {
        String patientID = "04048";
        // We decided that the start time does not matter.
        // We are supposed to get just the resat of the patient ECG file, since the last
        // supposed reading. Recall that we have a continuous 10 heures ECG file
        // which we will slice into consecutive (hence ignoring startTime) ECG slices of the
        // appropriate <code>duration</code>s, which are supposed to be determined
        // dynamically based on rules.
        long durationInMinutes = 5;
        long durationInSeconds = 300;
        Instant startTime = Instant.now();

        // we use a default duration of 5 minutes
        Duration duration = Duration.ofMinutes(durationInMinutes);

        ECGFile ecgFile = ECGFileImpl.getECGFileForRecording(patientID,ECGFormat.CSV,startTime,duration);

        assertEquals(startTime,ecgFile.getStartRecordingTime(),"ecgFile is supposed to have a starting time of "+startTime);
        assertEquals(ecgFile.getEndRecordingTime(), ecgFile.getStartRecordingTime().plusSeconds(durationInSeconds));
        assertEquals(ECGFileImpl.DATA_ROOT_DIRECTORY + patientID+"/",ecgFile.getFilePath(),"The ECG file does not have the appropriate path");
        assertTrue(ecgFile.getFileName().startsWith(patientID),"The ECGFile name does not start with the paptient ID");
    }

    @Test
    public void testConsecutiveAcessPatientECGFiles() {
        String patientID = "04048";
        // We decided that the start time does not matter.
        // We are supposed to get just the resat of the patient ECG file, since the last
        // supposed reading. Recall that we have a continuous 10 heures ECG file
        // which we will slice into consecutive (hence ignoring startTime) ECG slices of the
        // appropriate <code>duration</code>s, which are supposed to be determined
        // dynamically based on rules.
        long durationInMinutes = 5;
        long durationInSeconds = 300;
        Instant startTime = Instant.now();

        // we use a default duration of 5 minutes
        Duration duration = Duration.ofMinutes(durationInMinutes);

        ECGFile ecgFile_1 = ECGFileImpl.getECGFileForRecording(patientID,ECGFormat.CSV,startTime,duration);

        // assume that the next ECG is also 5 minutes long, an hour later
        Instant ecgFile_2_startTime = startTime.plusSeconds(3600);
        ECGFile ecgFile_2 = ECGFileImpl.getECGFileForRecording(patientID,ECGFormat.CSV,ecgFile_2_startTime,duration);

        // assume that the next ECG is also 5 minutes long, an hour later
        Instant ecgFile_3_startTime = ecgFile_2_startTime.plusSeconds(3600);
        ECGFile ecgFile_3 = ECGFileImpl.getECGFileForRecording(patientID,ECGFormat.CSV,ecgFile_3_startTime,duration);

        assertEquals(startTime,ecgFile_1.getStartRecordingTime(),"ecgFile is supposed to have a starting time of "+startTime);
        assertEquals(ecgFile_1.getEndRecordingTime(), ecgFile_1.getStartRecordingTime().plusSeconds(durationInSeconds));
        assertEquals(ECGFileImpl.DATA_ROOT_DIRECTORY + patientID+"/",ecgFile_1.getFilePath(),"The ECG file does not have the appropriate path");
        assertTrue(ecgFile_1.getFileName().startsWith(patientID),"The ECGFile name does not start with the paptient ID");

        //assertEquals(startTime,ecgFile_2.getStartRecordingTime(),"ecgFile is supposed to have a starting time of "+ecgFile_2_startTime);
        assertEquals(ecgFile_2.getEndRecordingTime(), ecgFile_2.getStartRecordingTime().plusSeconds(durationInSeconds));
        assertEquals(ECGFileImpl.DATA_ROOT_DIRECTORY + patientID+"/",ecgFile_2.getFilePath(),"The ECG file does not have the appropriate path");
        assertTrue(ecgFile_2.getFileName().startsWith(patientID),"The ECGFile name does not start with the paptient ID");
    }

    @Test
    public void testRelativePaths() {
        Path path = Paths.get("../../../");

        System.out.println("Printing the contents of the directory ../../../");

        // try-with-resources ensures the stream closes and releases OS resources
        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
