package spa.samples.heartpatientmonitoring.heartmonitor.types.ecg;

import java.time.Instant;

public interface ECGFile {

    public ECGFormat getFormat();

    public String getFileName();

    public String getFilePath();

    public Instant getStartRecordingTime();

    public Instant getEndRecordingTime();

}
