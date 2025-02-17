package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;
import java.awt.Color;

public class KPajarito extends KRectangle {
	public static double PA_X_VELOCITY = 0;
	public static double PA_WIDTH = 100, PA_HEIGHT = PA_WIDTH;
	public static double PA_INITIAL_X = 100;
	public static double U_ACC = -12, U_ACC_DEC = -4;
	
	public Vector upwardsAcc;
	
	public KPajarito() {
		setPosicion( new Vector(PA_INITIAL_X, World.worldH / 2) );
		setVelocidad( new Vector(PA_X_VELOCITY, 0) );
		setAceleracion( World.gravity );
		setWidth(PA_WIDTH);
		setHeight(PA_HEIGHT);
		upwardsAcc = new Vector(0, 0);
		
		this.color = Color.BLACK;
	}
	
	@Override
	public void update() {
		velocidad = Vector.suma( velocidad, upwardsAcc );
		super.update();
		if(upwardsAcc.y < 0)
			upwardsAcc.y -= U_ACC_DEC;
		if(upwardsAcc.y > 0)
			upwardsAcc.y = 0;
	}
	
	public void setUpwardsAcceleration() {
		upwardsAcc.y = U_ACC;
	}
}
