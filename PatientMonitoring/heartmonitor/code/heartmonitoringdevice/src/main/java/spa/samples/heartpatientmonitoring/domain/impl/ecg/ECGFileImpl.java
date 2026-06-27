package spa.samples.heartpatientmonitoring.domain.impl.ecg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.HashMap;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class ECGFileImpl implements ECGFile {

    public static String DATA_ROOT_DIRECTORY = "../../../Cardiology datasets/MIT-BIH-Atrial-Fibrillation-dataset-sample/";

    public static String ECG_FILE_SUFFIX = "_ekg.csv";

    /**
     * this indicates the number of ECG measurements per second for 
     * the MIT-BIH Atrial Fibrillation Dataset
     */
    public static int ECG_FREQUENCY = 250;

    private static HashMap<String,Long> fileCursors = new HashMap<>();

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
        try {
            Path patientFullECGPath = Paths.get(DATA_ROOT_DIRECTORY+patientID+"/"+nameECGFile);

            // check the curson position for that file. if it is the first time
            // it means that it is the first time we access this patient's ECG
            long startCursor = 0;
            long endCursor = 0;
            if (fileCursors.containsKey(nameECGFile)) {
                startCursor = fileCursors.get(nameECGFile);
            }
            endCursor =  startCursor + duration.getSeconds()*ECG_FREQUENCY;
            fileCursors.put(nameECGFile,Long.valueOf(endCursor));

            // check if we have afile with rows [startCursor,...,endCursor -1], which is
            // supposed to correspond to the desired ECG, if not create one
            String fileNameECG = patientID + "_"+ startCursor + "_" + (endCursor -1) + ECG_FILE_SUFFIX;

            String fullFileNameECG = DATA_ROOT_DIRECTORY+patientID+"/"+fileNameECG;
            File ecgFile = new File(fullFileNameECG);

            // if it doesn't exist, create it and populate it
            if (!ecgFile.exists()) {
                String firstLine = ",ECG1,ECG2,qrs_annotation,generic_annotation";

                /////
                Path pathECGFile = Path.of(fullFileNameECG);
                /////
                long linesToSkip = (startCursor > 0)? startCursor -1 : 0;
                Files.writeString(pathECGFile,firstLine + "\n",StandardOpenOption.CREATE);
                Files.lines(patientFullECGPath).skip(linesToSkip).limit(endCursor-startCursor).forEach((line) -> {
                    try {
                        Files.writeString(pathECGFile,line+ "\n",StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            return new FileID(fileNameECG, DATA_ROOT_DIRECTORY+patientID+"/");
        } catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
