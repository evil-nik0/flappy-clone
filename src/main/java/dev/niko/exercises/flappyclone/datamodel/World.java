package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;

public class World {
	public static double worldW = 2080, worldH = 1170; // 16/9
	public static Vector gravity = new Vector( 0, 1 );
	
	static {
		if(worldH%2 != 0 || worldW%2 != 0) throw new RuntimeException("Las dimensiones del mundo deben ser números pares!");
	}
}
