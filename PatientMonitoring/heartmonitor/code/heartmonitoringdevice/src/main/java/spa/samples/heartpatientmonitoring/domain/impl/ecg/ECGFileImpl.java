package spa.samples.heartpatientmonitoring.domain.impl.ecg;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFile;
import spa.samples.heartpatientmonitoring.domain.types.ecg.ECGFormat;

public class ECGFileImpl implements ECGFile {

    public static record FileID(String fileName, String filePath) {
    }

    private ECGFormat format;
    private FileID fileID;
    private java.time.Instant startRecordingTime;
    private java.time.Instant endRecordingTime;

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

    public static ECGFile getECGFileForRecording(ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        FileID fileID = getFileID(format, startTime, duration);
        Instant endTime = startTime.plus(duration);
        return new ECGFileImpl(format, fileID.fileName(), fileID.filePath(), startTime, endTime);
    }
    
    private static FileID getFileID(ECGFormat format, java.time.Instant startTime, java.time.Duration duration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileID'");
    }
}
