package spa.samples.heartpatientmonitoring.domain.impl.util;
import java.time.Instant;

import spa.samples.heartpatientmonitoring.domain.types.util.Location;
import spa.samples.heartpatientmonitoring.domain.types.util.Measurement;
import spa.samples.heartpatientmonitoring.domain.types.util.MeasurementType;

public class MeasurementImpl implements Measurement {

    private Instant startTime;
    private Instant endTime;
    private Location startLocation;
    private Location endLocation;  
    private MeasurementType measurementType;

    /**
     * a convenience constructor to create a measurement with the start and end time set to the current time, 
     * and the measurement type set to the provided value
     * 
     * The start and end time can be updated later using the setter methods.
     * @param measurementType
     */
    public MeasurementImpl(MeasurementType measurementType) {
        this(measurementType, Instant.now(), Instant.now());
    }

    public MeasurementImpl(MeasurementType measurementType,Instant startTime, Instant endTime) {
        this.measurementType = measurementType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public Location getStartLocation() {
        return startLocation;
    }

    @Override
    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    @Override
    public Location getEndLocation() {
        return endLocation;
    }

    @Override
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }



    @Override
    public MeasurementType getMeasurementType() {
        return measurementType;
    }
    
}
