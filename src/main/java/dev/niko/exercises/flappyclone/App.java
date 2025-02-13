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
	trees.add( new KTree().setX(World.worldW / 3) );
	trees.add( new KTree().setX(2 * World.worldW / 3) );
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

		pres.repaint();
		
		initialTime = System.currentTimeMillis();
	}
    }
}
