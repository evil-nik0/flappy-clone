package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;
import java.awt.Color;

public class KBackground extends KRectangle {
	public static double B_VELOCITY = -20;
	
	public KBackground() {
		setVelocidad(new Vector( B_VELOCITY, 0 ));
		setAceleracion(new Vector( 0, 0 ));
		setWidth(World.worldW);
		setHeight(World.worldH);
	}
	
	public KBackground ubicarEnOrigen() {
		setPosicion(new Vector( 0, 0 ));
		return this;
	}
	public KBackground ubicarALaDerecha() {
		setPosicion(new Vector( World.worldW, 0 ));
		return this;
	}
	public KBackground setColor(Color color) {
		this.color = color;
		return this;
	}
	@Override
	public void update() {
		super.update();
	}
}
