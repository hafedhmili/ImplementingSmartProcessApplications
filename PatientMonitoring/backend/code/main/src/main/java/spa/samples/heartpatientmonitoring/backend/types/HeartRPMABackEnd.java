package spa.samples.heartpatientmonitoring.backend.types;

import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.ecg.ECG;

public interface HeartRPMABackEnd {

    public boolean isNotAligned(ECG oldECG, Instant oldTime, ECG latestECG, Instant latestTime);

    public void handleDifference(ECG oldECG, Instant oldTime, ECG latestECG, Instant latestTime);

    public boolean isProblematic(ECG latestECG, Instant latestTime);

    public void handleProblematicECG(ECG latestECG, Instant latestTime);

    public boolean needsToCheck(ECG latestECG, Instant oldTime, Instant latestTime);

    /**
     * This is the method that will be called by the event Grid when a new ECG arrives. This method
     * will add the new ECG to a processing queue for the HeartRPMABackEnd.
     * 
     * This will be implemented in a separate thread from the start() method so that we are able
     * to record the arrival of new ECGs while the processing loop is running.
     * @param latestECG
     */
    public void newECG(ECG latestECG);

    /**
     * This is the method that starts the processing loop of the HeartRPMABackEnd. It will start a thread
     * that will implement the processing loop.
     */
    public void start();

    /**
     * This method will return the next ECG that needs to be processed by the HeartRPMABackEnd. It will 
     * retrieve that the ECG that is at the top of the ECG queue. 
     * @return
     */
    public ECG getNextECG();
    
}
