package spa.samples.heartpatientmonitoring.domain.impl.ecg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class ECGFileImpl implements ECGFile {

    private static String DATA_ROOT_DIRECTORY = "../../../Cardiology datasets/MIT-BIH-Atrial-Fibrillation-dataset-sample/";

    private static String ECG_FILE_SUFFIX = "_ekg.csv";

    /**
     * this indicates the number of ECG measurements per second for 
     * the MIT-BIH Atrial Fibrillation Dataset
     */
    private static int ECG_FREQUENCY = 250;

    private static HashMap<String,Integer> fileCursors = new HashMap<>();

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

    /**
     * This method will extract a data file from the MIT-BIH-Atrial-Fibrillation dataset. That dataset includes a single
     * 10-hour ECG, for each (of 83) patient. For the purposes of the POC, we will consume the 10-hour ECG files, a 
     * <code>duration</code> segment at a time, pretending that it was taken at <code>startTime</code>. In reality, we will
     * simply take the next segment of the MIT-BIH ECG of length <code>duration</code>, ignoring the start time.
     * @param patientID
     * @param format
     * @param startTime
     * @param duration
     * @return
     */
    public static ECGFile getECGFileForRecording(String patientID, ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        FileID fileID = getFileID(patientID,format, startTime, duration);
        Instant endTime = startTime.plus(duration);
        return new ECGFileImpl(format, fileID.fileName(), fileID.filePath(), startTime, endTime);
    }
    
    /**
     * This method returns a FileID record that represents the ECG of the patient with id <code>patientID</code>, with
     * ECG format <code>format</code>, with the start time <code>startTime</code> and <code>duration</code>.
     * 
     * For the time being, we will just decompose the 10-hour patient to retrieve an arbitrary file from the corresponding directory
     * @param patientID
     * @param format
     * @param startTime
     * @param duration
     * @return
     */
    private static FileID getFileID(String patientID, ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        // first, we get the file containing the patient's ECG
        String nameECGFile= patientID+ECG_FILE_SUFFIX;
        FileReader patientECGFileReader = null;
        try {
            patientECGFileReader = new FileReader(DATA_ROOT_DIRECTORY+nameECGFile);

            // check the curson position for that file. if it is the first time
            // it means that it is the first time we access this patient's ECG
            int startCursor = 0;
            if (fileCursors.containsKey(nameECGFile)) {
                startCursor = fileCursors.get(nameECGFile);
            }
            long endCursor =  startCursor + duration.getSeconds()*ECG_FREQUENCY;

            // check if we have afile with rows [startCursor,...,endCursor -1], which is
            // supposed to correspond to the desired ECG, if not create one
            String fileNameECG = patientID + "_"+ startCursor + "_" + (endCursor -1);

            // @TODO: code to check existence of file, and create if needed

            patientECGFileReader.close();
            return new FileID(fileNameECG, DATA_ROOT_DIRECTORY);
        } catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
