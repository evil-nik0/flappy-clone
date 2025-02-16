package dev.niko.exercises.flappyclone.datamodel;

public class PhysicsEngine {

	//la devolucion es {MTV en magnitud, angulo de la normal}; en Pong puede ser 0 90° o 45° si es un choque justo justo con la esquina de la raqueta, o -1 si no hay colision
	//-pero señor programador, no sería mejor que la función devolviera un objeto con campos MTV y Vector normal ?
	//-cierra la boca niña, no ves que estoy sufriendo de un ataque de fiaca!!
	public static double[] areRectanglesColliding(double r1x, double r1y, double r1width, double r1height,
						      double r2x, double r2y, double r2width, double r2height ) {
		//d is for deepness
		
		double dx2 = (r1x + r1width) - r2x;
		double dx1 = (r2x + r2width) - r1x;
		double dy2 = (r1y + r1height) - r2y;
		double dy1 = (r2y + r2height) - r1y;
		
		if(dx1 * dx2 < 0 || dy1 * dy2 < 0) return new double[] {0, -1};
		
		dx1 = Math.abs(dx1) < Math.abs(dx2) ? Math.abs(dx1) : Math.abs(dx2); //usamos la misma variable para guardar la profundidad mas chica
		dy1 = Math.abs(dy1) < Math.abs(dy2) ? Math.abs(dy1) : Math.abs(dy2);
		
		if(dx1 < dy1) return new double[] {dx1, 0}; //la profundidad más corta está en el eje de abscisas
		else if(dy1 < dx1) return new double[] {dy1, 90}; //la profundidad más corta está en el eje de ordenadas
		else return new double[] {dx1, 45}; //chocaron justo vértice con vértice, caso RARO pero puede pasar; es aún más raro dado que se usan doubles y no ints
	}
}
