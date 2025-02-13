package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;
import java.awt.Color;

public class KPajarito extends KRectangle {
	public static double PA_X_VELOCITY = 0;
	public static double PA_WIDTH = 30, PA_HEIGHT = 30;
	public static double PA_INITIAL_X = 100;
	
	public KPajarito() {
		setPosicion( new Vector(PA_INITIAL_X, World.worldH / 2) );
		setVelocidad( new Vector(PA_X_VELOCITY, 0) );
		setAceleracion( World.gravity );
		setWidth(PA_WIDTH);
		setHeight(PA_HEIGHT);
		
		this.color = Color.BLACK;
	}
	
	@Override
	public void update() {
		super.update();
	}
}
