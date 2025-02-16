package dev.niko.exercises.flappyclone.datamodel;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class ModelExtension {
	public GameState gs;
	public boolean seBorroUnPipe;
    	public Controles ctrles;
	public KPajarito pajarito;
	public List<KBackground> bgs;
	public List<KPipe> pipes;
	public List<KTree> trees;
	
	public ModelExtension() {
		ctrles = new Controles();
		initializeModelExt();
	}
	
	public ModelExtension initializeModelExt() {
		gs = GameState.PLAYING;
		seBorroUnPipe = false;
		pajarito = new KPajarito();
		bgs = new ArrayList<>();
		bgs.add( new KBackground().ubicarEnOrigen().setColor(Color.WHITE) ); 
		bgs.add( new KBackground().ubicarALaDerecha().setColor(Color.WHITE) );
		pipes = new ArrayList<>();
		pipes.add( new KPipe().setX(World.worldW/10) );
		pipes.add( new KPipe().setX(3 * World.worldW/10) );
		pipes.add( new KPipe().setX(5 * World.worldW/10) );
		pipes.add( new KPipe().setX(7 * World.worldW/10) );
		pipes.add( new KPipe().setX(9 * World.worldW/10) );
		trees = new ArrayList<>();
		trees.add( new KTree().setX(World.worldW / 6) );
		trees.add( new KTree().setX(3 * World.worldW / 6) );
		trees.add( new KTree().setX(5 * World.worldW / 6) );
		trees.add( new KTree().setX(2 * World.worldW / 6) );
		trees.add( new KTree().setX(4 * World.worldW / 6) );
		trees.sort( (t1, t2) -> KTree.compare(t1, t2) );
		
		return this;
	}
	
	public void gsPlayingLoop() {
    		pajarito.update();
		for(KBackground bg : bgs)
			bg.update();
		for(KPipe pipe : pipes)
			pipe.update();
		for(KTree tree : trees)
			tree.update();
			
		//chequear si hace falta eliminar árboles, pipes o un background
		for(int i = 0; i < pipes.size(); i++)
			if(pipes.get(i).smallUR.posicion.x+pipes.get(i).smallUR.width < 0) {
				pipes.remove(i);
				seBorroUnPipe = true;
			}
		for(int i = 0; i < trees.size(); i++)
			if(trees.get(i).copa.posicion.x+trees.get(i).copa.width < 0)
				trees.remove(i);
				for(int i = 0; i < bgs.size(); i++)
			if(bgs.get(i).posicion.x + bgs.get(i).width < 0)
				bgs.remove(i);
		
		//chequear si hace falta crear un nuevo pipe, tree o background
		if( Math.random() < 1/KTree.PROBABILIDAD_DE_CREAR_NUEVO_TREE ) {
			KTree tree = new KTree();
			if(trees.size() == 0 || !trees.get(trees.size() - 1).overlapsWith(tree) )
				trees.add(tree);
				trees.sort( (t1, t2) -> KTree.compare(t1, t2) );
		}
		if(seBorroUnPipe) {
			pipes.add( new KPipe() );
			seBorroUnPipe = false;
		}
		if(bgs.size() == 1)
			bgs.add( new KBackground().ubicarALaDerecha().setColor(Color.WHITE) );
		
		//si se presionó espacio, se acelera hacia arriba al pajarito
		if(ctrles.spacePressed) {
			pajarito.velocidad.y = 0;
			pajarito.setUpwardsAcceleration();
			ctrles.spacePressed = false;
		}
		
		//se chequean colisiones
		for(KPipe pipe : pipes)
			if( PhysicsEngine.areRectanglesColliding(pajarito.posicion.x, pajarito.posicion.y, pajarito.width, pajarito.height, 
							pipe.smallDR.posicion.x, pipe.smallDR.posicion.y, pipe.smallDR.width, pipe.smallDR.height)[1] != -1 )
				gs = GameState.OVER;
			else 
			if( PhysicsEngine.areRectanglesColliding(pajarito.posicion.x, pajarito.posicion.y, pajarito.width, pajarito.height, 
							pipe.smallUR.posicion.x, pipe.smallUR.posicion.y, pipe.smallUR.width, pipe.smallUR.height)[1] != -1 )
				gs = GameState.OVER;
			else 
			if( PhysicsEngine.areRectanglesColliding(pajarito.posicion.x, pajarito.posicion.y, pajarito.width, pajarito.height, 
							pipe.largeUR.posicion.x, pipe.largeUR.posicion.y, pipe.largeUR.width, pipe.largeUR.height)[1] != -1 )
				gs = GameState.OVER;
			else 
			if( PhysicsEngine.areRectanglesColliding(pajarito.posicion.x, pajarito.posicion.y, pajarito.width, pajarito.height, 
							pipe.largeDR.posicion.x, pipe.largeDR.posicion.y, pipe.largeDR.width, pipe.largeDR.height)[1] != -1 )
				gs = GameState.OVER;
		
    	}
}
