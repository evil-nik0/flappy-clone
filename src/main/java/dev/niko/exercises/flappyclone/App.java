package dev.niko.exercises.flappyclone;

import dev.niko.exercises.flappyclone.datamodel.*;
import dev.niko.exercises.flappyclone.presentation.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class App {

	public static int FPS = 60;
	public static GameState gs;

    public static void main(String[] args) throws Exception {
    	gs = GameState.PLAYING;
    	long mSPF = Math.floorDiv(1000, FPS), initialTime;
    	boolean seBorroUnPipe = false;
	KPajarito pajarito = new KPajarito();
	List<KBackground> bgs = new ArrayList<>();
	bgs.add( new KBackground().ubicarEnOrigen().setColor(Color.WHITE) ); 
	bgs.add( new KBackground().ubicarALaDerecha().setColor(Color.BLUE) );
	List<KPipe> pipes = new ArrayList<>();
	pipes.add( new KPipe().setX(World.worldW/10) );
	pipes.add( new KPipe().setX(3 * World.worldW/10) );
	pipes.add( new KPipe().setX(5 * World.worldW/10) );
	pipes.add( new KPipe().setX(7 * World.worldW/10) );
	pipes.add( new KPipe().setX(9 * World.worldW/10) );
	List<KTree> trees = new ArrayList<>();
	trees.add( new KTree().setX(World.worldW / 6) );
	trees.add( new KTree().setX(3 * World.worldW / 6) );
	trees.add( new KTree().setX(5 * World.worldW / 6) );
	trees.add( new KTree().setX(2 * World.worldW / 6) );
	trees.add( new KTree().setX(4 * World.worldW / 6) );
	trees.sort( (t1, t2) -> KTree.compare(t1, t2) );
	SimplePresentation pres = new SimplePresentation(pajarito, bgs, pipes, trees);
	pres.init();
	
	initialTime = System.currentTimeMillis();
	while(true) if( System.currentTimeMillis() - initialTime > mSPF ) {
		
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
		
			
		pres.repaint();
		
		initialTime = System.currentTimeMillis();
	}
    }
}
