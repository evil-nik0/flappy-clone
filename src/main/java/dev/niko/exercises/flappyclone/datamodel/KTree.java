package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.*;
import java.awt.Color;

public class KTree {
	public static double TREES_VEL = -10;
	public static double C_W = 100, C_H = 100;
	public static double T_W = 50;
	public static double TH_MIN = 50, TH_MAX = 300;
	public static double PROBABILIDAD_DE_CREAR_NUEVO_TREE = 30;
	
	static {
		if(T_W >= C_W) throw new RuntimeException("El ancho de los troncos debe ser menor al ancho de la copa!!");
		if(C_W%2 != 0 || T_W%2 != 0) throw new RuntimeException("Tanto el ancho de la copa cómo del tronco deben ser pares!!");
	}
	
	public KRectangle copa, tronco;
	
	//crea un árbol en el extremo derecho del mundo
	public KTree() {
		double alturaTronco = Math.random() * ( TH_MAX - TH_MIN ) + TH_MIN;
		
		tronco = new KRectangle()
			.setPosicion(new Vector( World.worldW-T_W, World.worldH - alturaTronco ))
			.setVelocidad(new Vector( TREES_VEL, 0 ))
			.setAceleracion(new Vector( 0, 0 ))
			.setWidth(T_W)
			.setHeight(alturaTronco);
		copa = new KRectangle()
			.setPosicion(new Vector( World.worldW - T_W - (C_W-T_W)/2, World.worldH - alturaTronco - C_H ))
			.setVelocidad(new Vector( TREES_VEL, 0 ))
			.setAceleracion(new Vector( 0, 0 ))
			.setWidth(C_W)
			.setHeight(C_H);
		
		tronco.color = Color.GRAY;
		copa.color = Color.GREEN;
	}
	
	public void update() {
		copa.update();
		tronco.update();
	}
	
	public KTree setX(double x) {
		if(copa == null || tronco == null) throw new RuntimeException("KTree.setX() tiene que ser llamado en un ktree ya creado");
		
		copa.setX(x);
		tronco.setX( x + C_W/2 - T_W/2 );
		
		return this;
	}
	
	public static int compare(KTree t1, KTree t2) {
		if(t1.copa.posicion.x < t2.copa.posicion.x) return -1;
		else if(t1.copa.posicion.x == t2.copa.posicion.x) return 0;
		else return 1;
	}
	
	public boolean overlapsWith(KTree t) {
		double deepness = Intervals.overlappingDeepness( this.copa.posicion.x, this.copa.posicion.x + this.copa.width, 
								  t.copa.posicion.x, t.copa.posicion.x + t.copa.width);
		if(deepness == -1 || deepness == 0) return false;
		else return true;
	}
	
	@Override
	public String toString() {
		return String.format("Copa: %s, Tronco: %s", copa.toString(), tronco.toString());
	}
}








