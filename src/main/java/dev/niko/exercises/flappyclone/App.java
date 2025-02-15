package dev.niko.exercises.flappyclone;

import dev.niko.exercises.flappyclone.datamodel.*;
import dev.niko.exercises.flappyclone.presentation.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class App {

	public static int FPS = 30;
	public static GameState gs;

    public static void main(String[] args) throws Exception {
    	gs = GameState.PLAYING;
    	long mSPF = Math.floorDiv(1000, FPS), initialTime;
	KPajarito pajarito = new KPajarito();
	KBackground bg1 = new KBackground().ubicarEnOrigen().setColor(Color.WHITE), bg2 = new KBackground().ubicarALaDerecha().setColor(Color.BLUE);
	List<KPipe> pipes = new ArrayList<>();
	pipes.add( new KPipe().setX(World.worldW/4) );
	pipes.add( new KPipe().setX(2 * World.worldW/4) );
	pipes.add( new KPipe().setX(3 * World.worldW/4) );
	List<KTree> trees = new ArrayList<>();
	trees.add( new KTree().setX(World.worldW / 6) );
	trees.add( new KTree().setX(3 * World.worldW / 6) );
	trees.add( new KTree().setX(5 * World.worldW / 6) );
	trees.add( new KTree().setX(2 * World.worldW / 6) );
	trees.add( new KTree().setX(4 * World.worldW / 6) );
	trees.sort( (t1, t2) -> KTree.compare(t1, t2) );
	SimplePresentation pres = new SimplePresentation(pajarito, bg1, bg2, pipes, trees);
	pres.init();
	
	initialTime = System.currentTimeMillis();
	while(true) if( System.currentTimeMillis() - initialTime > mSPF ) {
		
		pajarito.update();
		bg1.update();
		bg2.update();
		for(KPipe pipe : pipes)
			pipe.update();
		for(KTree tree : trees)
			tree.update();
			
		/*
		//chequear si hace falta eliminar árboles, pipes o un background
		for(int i = 0; i < pipes.size(); i++)
			if(pipes.get(i).smallUR.posicion.x+pipes.get(i).smallUR.width < 0)
				pipes.remove(i);
		for(int i = 0; i < trees.size(); i++)
			if(trees.get(i).copa.posicion.x+trees.get(i).copa.width < 0)
				trees.remove(i);
		
		if(bg1.posicion.x + bg1.width < 0) {
			bg1 = bg2;
			bg2 = null;
		}
		*/
		

		pres.repaint();
		
		initialTime = System.currentTimeMillis();
	}
    }
}
