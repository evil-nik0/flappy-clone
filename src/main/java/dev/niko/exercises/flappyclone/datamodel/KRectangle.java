package dev.niko.exercises.flappyclone.datamodel;

import dev.niko.utils.Vector;
import java.awt.Color;

public class KRectangle {
	public Vector posicion, velocidad, aceleracion;
	public double width, height;
	
	public Color color; //solo hasta crear las clases de la presentación
	
	public KRectangle() {
		posicion = velocidad = aceleracion = null;
		width = height = -1F;
	}
	public KRectangle setPosicion(Vector posicion) {
		this.posicion = posicion;
		return this;
	}
	public KRectangle setX(double x) {
		if(posicion == null) throw new RuntimeException("KRectangle.setX() debe ser llamado sólo si ya se ha configurado una posición");
		
		posicion = new Vector(x, posicion.y);
		return this;
	}
	public KRectangle setVelocidad(Vector velocidad) {
		this.velocidad = velocidad;
		return this;
	}
	public KRectangle setAceleracion(Vector aceleracion) {
		this.aceleracion = aceleracion;
		return this;
	}
	public KRectangle setWidth(double width) {
		this.width = width;
		return this;
	}
	public KRectangle setHeight(double height) {
		this.height = height;
		return this;
	}
	
	public void update() {
		velocidad = Vector.suma(aceleracion, velocidad);
		posicion = Vector.suma(velocidad, posicion);
	}
	
	@Override
	public String toString() {
		return String.format("[%f, %f]", posicion.x, posicion.y);
	}
}



