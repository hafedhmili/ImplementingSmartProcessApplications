package spa.samples.heartpatientmonitoring.domain.types;

import java.util.Iterator;

public interface ECGAnalysisReport {
	
	public Interval getRhythm();
	
	public void setRhythm(Interval interval);
	
	public RhythmType getRhythmType();
	
	public void setRhythmType(RhythmType rythmType);
	
	public ShapePWaves getShapePWaves();
	
	public void setShapePWaves(ShapePWaves pWavesType);
	
	public NatureQRSComplexes getNatureQRSComplexes();
	
	public void setNatureQRSComplexes(NatureQRSComplexes natureQRSComplexes);
	
	public Iterator<ECGClassification> getClassifications();
	
	public void addClassification(ECGClassification classification, float confidenceLevel);
	
	public float removeClassification(ECGClassification classficationConfidence);
	
	public float getConfidenceLevelForClassification(ECGClassification classification);

}
