package dev.niko.exercises.flappyclone.datamodel;

public class DataModelExtensionAnswer {
	public boolean wasACollision;
	public long currentTime;
	
	public DataModelExtensionAnswer(long currentTime) {
		wasACollision = false;
		this.currentTime = currentTime;
	}
}
