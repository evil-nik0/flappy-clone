package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;

public class World {
	public static double worldW = 4160, worldH = 2340; // se puede colocar cualquier tamaño para el mundo virtual y para la resolución de la ventana, pero deben mantener
	//el mismo ratio, e.g. 16/9 o 4/3
	public static Vector gravity = new Vector( 0, 1 );
	
	static {
		if(worldH%2 != 0 || worldW%2 != 0) throw new RuntimeException("Las dimensiones del mundo deben ser números pares!");
	}
}
