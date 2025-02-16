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
		gs = GameState.PLAYING;
		seBorroUnPipe = false;
	    	ctrles = new Controles();
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
	}
}
