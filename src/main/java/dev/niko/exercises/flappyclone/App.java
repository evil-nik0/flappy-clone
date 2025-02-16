package dev.niko.exercises.flappyclone;

import dev.niko.exercises.flappyclone.datamodel.*;
import dev.niko.exercises.flappyclone.presentation.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class App {

    public static int HBPS = 60;

    public static void main(String[] args) throws Exception {
    	long mSPHB = Math.floorDiv(1000, HBPS), initialTime;
    	ModelExtension extension = new ModelExtension();
	SimplePresentation pres = new SimplePresentation(extension);
	pres.init();
	pres.addKeyListener( extension.ctrles );
	
	initialTime = System.currentTimeMillis();
	while(true) if( System.currentTimeMillis() - initialTime > mSPHB ) {
		
		gsPlayingLoop(extension);
		pres.repaint();
		
		initialTime = System.currentTimeMillis();
	}
    }
    
    private static void gsPlayingLoop(ModelExtension e) {
    	e.pajarito.update();
	for(KBackground bg : e.bgs)
		bg.update();
	for(KPipe pipe : e.pipes)
		pipe.update();
	for(KTree tree : e.trees)
		tree.update();
		
	//chequear si hace falta eliminar árboles, pipes o un background
	for(int i = 0; i < e.pipes.size(); i++)
		if(e.pipes.get(i).smallUR.posicion.x+e.pipes.get(i).smallUR.width < 0) {
			e.pipes.remove(i);
			e.seBorroUnPipe = true;
		}
	for(int i = 0; i < e.trees.size(); i++)
		if(e.trees.get(i).copa.posicion.x+e.trees.get(i).copa.width < 0)
			e.trees.remove(i);
	for(int i = 0; i < e.bgs.size(); i++)
		if(e.bgs.get(i).posicion.x + e.bgs.get(i).width < 0)
			e.bgs.remove(i);
	
	//chequear si hace falta crear un nuevo pipe, tree o background
	if( Math.random() < 1/KTree.PROBABILIDAD_DE_CREAR_NUEVO_TREE ) {
		KTree tree = new KTree();
		if(e.trees.size() == 0 || !e.trees.get(e.trees.size() - 1).overlapsWith(tree) )
			e.trees.add(tree);
			e.trees.sort( (t1, t2) -> KTree.compare(t1, t2) );
	}
	if(e.seBorroUnPipe) {
		e.pipes.add( new KPipe() );
		e.seBorroUnPipe = false;
	}
	if(e.bgs.size() == 1)
		e.bgs.add( new KBackground().ubicarALaDerecha().setColor(Color.WHITE) );
	
	//si se presionó espacio, se acelera hacia arriba al pajarito
	if(e.ctrles.spacePressed) {
		e.pajarito.velocidad.y = 0;
		e.pajarito.setUpwardsAcceleration();
		e.ctrles.spacePressed = false;
	}
		
    }
}
