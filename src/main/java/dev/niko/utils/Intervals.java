package dev.niko.utils;

public class Intervals {

	public static double overlappingDeepness(double i1, double f1, double i2, double f2) {
		if(f1 < i1 || f2 < i2) throw new RuntimeException("El método Intervals.overlappingDeepness() no permite intervalos negativos!");
	
		double d1 = f2 - i1;
		double d2 = f1 - i2;
		
		if( d1*d2 < 0 ) return -1; //no hay solapamiento alguno
		else if( Math.abs(d1) < Math.abs(d2) ) return Math.abs(d1);
		else return Math.abs(d2);
	}

}
