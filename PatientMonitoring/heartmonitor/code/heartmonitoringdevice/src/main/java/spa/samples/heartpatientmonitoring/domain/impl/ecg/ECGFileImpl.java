package spa.samples.heartpatientmonitoring.domain.impl.ecg;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class ECGFileImpl implements ECGFile {

    private static String DATA_ROOT_DIRECTORY = "../../../Cardiology dataset/a-large-scale-12-lead-electrocardiogram-database-for-arrhythmia-study-1.0.0/WFDBRecords/";

    public static record FileID(String fileName, String filePath) {
    }

    private ECGFormat format;
    private FileID fileID;
    private Instant startRecordingTime;
    private Instant endRecordingTime;

    public ECGFileImpl(ECGFormat format, String fileName, String filePath, java.time.Instant startRecordingTime, java.time.Instant endRecordingTime) {
        this.format = format;
        this.fileID = new FileID(fileName, filePath);
        this.startRecordingTime = startRecordingTime;
        this.endRecordingTime = endRecordingTime;
    }

    @Override
    public ECGFormat getFormat() {
        return format;
    }

    @Override
    public String getFileName() {
        return fileID.fileName();
    }

    @Override
    public String getFilePath() {
        return fileID.filePath();
    }

    @Override
    public java.time.Instant getStartRecordingTime() {
        return startRecordingTime;
    }

    @Override
    public java.time.Instant getEndRecordingTime() {
        return endRecordingTime;
    }

    public static ECGFile getECGFileForRecording(String patientID, ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        FileID fileID = getFileID(patientID,format, startTime, duration);
        Instant endTime = startTime.plus(duration);
        return new ECGFileImpl(format, fileID.fileName(), fileID.filePath(), startTime, endTime);
    }
    
    /**
     * This method returns a FileID record that represents the ECG of the patient with id <code>patientID</code>, with
     * ECG format <code>format</code>, with the start time <code>startTime</code> and <code>duration</code>.
     * 
     * For the time being, we will just decompose the patient to retrieve an arbitrary file from the corresponding directory
     * @param patientID
     * @param format
     * @param startTime
     * @param duration
     * @return
     */
    private static FileID getFileID(String patientID, ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        // first, we break the patient id aroud the "."
        String[] idComponents = patientID.split(".");
        String topDirectory = idComponents[0];
        String lowerDirectory = idComponents[1];
        String filePath = DATA_ROOT_DIRECTORY + "/" + topDirectory+ "/"+ lowerDirectory + "/";
        String fileName = null;

        // now, pîck an arbitrary .MAT file within the directory DATA_ROOT_DIRECTORY/topDirectory/lowerDirectory
        // Each such directory contains a RECORDS file which lists the various file names (typically, 100 names).
        // In the actual directory, we will find a pair of files for each file name: FNAME.hea, and FNAME.MAT.
        // The first (.hed for header) contains file metadata. The second (.mat) contains the actual ECG
        // in .mat (MATLAB) format.
        // Accordingly, we need to read the RECORDS file in DATA_ROOT_DIRECTORY/topDirectory/lowerDirectory, check
        // its size (most contain 100 entries, except the last one which contains two entries), and pick
        // one .MAT at random.
        // Later, we will get smarter about it (e.g. take only the ECGs related to a patient of same age and gender
        // as the hypothetical patient)

        return new FileID(fileName, filePath);
    }
}
