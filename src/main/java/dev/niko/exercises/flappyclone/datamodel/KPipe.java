package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;
import java.awt.Color;

public class KPipe {
	public static double PIPE_VELOCITY = -5;
	public static double SRW = 40, SRH = 20, LRW = 30; //SRW es small rectangle width, etc.
	public static double MINIMUMLRH = 30;
	public static double DISTBETWPIPES = 300; //between the top of the pipes
	
	static {
		if(DISTBETWPIPES % 2 != 0) throw new RuntimeException("La distancia entre las pipes debería ser un número par, creeeo");
		if(SRW%2 != 0 || LRW%2 != 0) throw new RuntimeException("Los width de todos los componentes de la pipe deben ser pares!!");
		if(SRW <= LRW) throw new RuntimeException("El ancho de la cabeza de la pipe debe ser mayor al ancho del cuerpo!!");
	}
	
	
	public KRectangle smallDR, largeDR, smallUR, largeUR;
	
	//éste constructor crea una KPipe en el extremo derecho del mundo
	public KPipe() {
		double delta = MINIMUMLRH + SRH + DISTBETWPIPES/2;
		double centerBetwPipes = Math.random() * ( World.worldH - 2 * delta ) + delta;
		
		smallDR = new KRectangle()
			.setPosicion(new Vector( World.worldW - SRW, centerBetwPipes + DISTBETWPIPES/2 ))
			.setVelocidad(new Vector(PIPE_VELOCITY, 0))
			.setAceleracion(new Vector(0, 0))
			.setWidth(SRW)
			.setHeight(SRH);
		smallUR = new KRectangle()
			.setPosicion(new Vector( World.worldW - SRW, centerBetwPipes - DISTBETWPIPES/2 - SRH))
			.setVelocidad(new Vector(PIPE_VELOCITY, 0))
			.setAceleracion(new Vector(0, 0))
			.setWidth(SRW)
			.setHeight(SRH);
		largeDR = new KRectangle()
			.setPosicion(new Vector( World.worldW - SRW/2 - LRW/2, centerBetwPipes + DISTBETWPIPES/2 + SRH ))
			.setVelocidad(new Vector(PIPE_VELOCITY, 0))
			.setAceleracion(new Vector(0, 0))
			.setWidth(LRW)
			.setHeight( World.worldH - centerBetwPipes - SRH - DISTBETWPIPES/2 );
		largeUR = new KRectangle()
			.setPosicion(new Vector( World.worldW - SRW/2 - LRW/2, 0 ))
			.setVelocidad(new Vector(PIPE_VELOCITY, 0))
			.setAceleracion(new Vector(0, 0))
			.setWidth(LRW)
			.setHeight( centerBetwPipes - SRH - DISTBETWPIPES/2 );
			
		smallDR.color = Color.BLACK;
		smallUR.color = Color.BLACK;
		largeDR.color = Color.BLACK;
		largeUR.color = Color.BLACK;
	}
	
	public void update() {
		smallDR.update();
		largeDR.update();
		smallUR.update();
		largeUR.update();
	}
	
	public KPipe setX(double x) {
		if( smallDR == null || largeDR == null || smallUR == null || largeUR == null ) 
			throw new RuntimeException("KPipe.setX() tiene que ser llamado en un kpipe ya configurado");
		
		smallDR.setX( x );
		largeDR.setX( x + SRW/2 - LRW/2 );
		smallUR.setX( x );
		largeUR.setX( x + SRW/2 - LRW/2 );
		
		return this;
	}
}
