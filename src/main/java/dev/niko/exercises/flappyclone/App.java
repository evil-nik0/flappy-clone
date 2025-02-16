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
    	ModelExtension extension = new ModelExtension().initializeModelExt();
	SimplePresentation pres = new SimplePresentation(extension);
	pres.init();
	pres.addKeyListener( extension.ctrles );
	
	initialTime = System.currentTimeMillis();
	while(true) if( System.currentTimeMillis() - initialTime > mSPHB ) {
		
		switch(extension.gs) {
			case PLAYING:
				extension.gsPlayingLoop();
				pres.repaint();
				break;
			case OVER:
				if(extension.ctrles.spacePressed) {
					extension.ctrles.spacePressed = false;
					extension.initializeModelExt();
				}
				break;
			default:
				
		}
		
		initialTime = System.currentTimeMillis();
	}
    }
}
