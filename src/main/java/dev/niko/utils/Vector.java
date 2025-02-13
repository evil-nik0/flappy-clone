package dev.niko.utils;

public class Vector {
	public double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector suma(Vector u, Vector v) {
		return new Vector(u.x+v.x, u.y+v.y);
	}
	
	public static Vector resta(Vector f, Vector i) {
		return new Vector( f.x - i.x, f.y - i.y );
	}
	
	//angulo está especificado en degrees, radianes es para nerds <-- eso lo escribí antes de saber que Math.cos y Math.sin usan radianes.
	//por qué nadie me lo dijo debo haber quedado cómo un idiota !!!
	public static Vector crearUnitario(double angulo) {
		if(angulo < 0) System.out.println("Se mandó un ángulo negativo a Vector.crearUnitario(), no estoy seguro si funciona bien con ésto: " + angulo);
		
		double radians = Math.toRadians(angulo);
		return new Vector(Math.cos(radians), Math.sin(radians));
	}
	
	public static Vector multiplicacionEscalar(Vector v, double escalar) {
		Vector respuesta = new Vector(0, 0);
		respuesta.x = v.x * escalar;
		respuesta.y = v.y * escalar;
		return respuesta;
	}
	
	public double modulo() {
		return Math.sqrt(x*x + y*y);
	}
	
	public static double productoEscalar(Vector u, Vector v) {
		return u.x * v.x + u.y * v.y;
	}
	
	public static Vector proyeccion(Vector v, Vector d) {
		if(d.modulo() != 1) System.out.println(
		String.format("Ojo! Se llamo a Vector.proyeccion() con un vector de dirección que no tiene módulo 1: v=%s, d=%s", v.toString(), d.toString())
		);
		
		return multiplicacionEscalar( d, productoEscalar(v, d) );
		
	}
	
	public static Vector reflejarConRespectoANormal(Vector v, Vector n) {
		if(n.modulo() != 1) System.out.println("Ojo! Se paso a reflejarConRespectoANormal() una normal con módulo distinto de 1, no sé si funciona así");
		
		Vector proyeccionEnNormal = Vector.proyeccion(v, n);
		Vector proyeccionEnPerpANormal = Vector.resta( v, proyeccionEnNormal );
		proyeccionEnNormal = Vector.multiplicacionEscalar(proyeccionEnNormal, -1);
		return Vector.suma(proyeccionEnNormal, proyeccionEnPerpANormal);
	}
	
	public String toString() {
		return String.format("(%f,%f)", x, y);
	}
}

