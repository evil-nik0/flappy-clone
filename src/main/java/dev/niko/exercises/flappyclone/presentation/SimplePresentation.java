package dev.niko.exercises.flappyclone.presentation;

import dev.niko.exercises.flappyclone.datamodel.*;
import dev.niko.utils.Vector;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SimplePresentation extends Frame {
	private DataModelIntension e;
	private Image doubleBuffer;
	
	public static int SCREEN_W = 1424, SCREEN_H = 801; // mirar anotación de World.java
	public static double RATIOBETWCOORDSYSTEMS = SCREEN_W / World.worldW; //if the two are 16/9 or 4/3, then the ratio between heights is the same
	
	public SimplePresentation(DataModelIntension e) {
		super();
		this.e = e;
	}
	
	public void init() {
		setSize(SCREEN_W, SCREEN_H);
		setSize(getWidth() + getInsets().left + getInsets().right, getHeight() + getInsets().top + getInsets().bottom);
		setTitle("Flappy Clone");
		setVisible(true);
		
		doubleBuffer = createImage(SCREEN_W, SCREEN_H);
		
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		} );
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		if(doubleBuffer == null) return;
		Graphics frameG = g;
		g = doubleBuffer.getGraphics();
		
		switch(e.gs) {
			case PLAYING:
				paintGsPlaying(g);
				break;
			case OVER:
				
				break;
			default:
			
		}
		
		frameG.drawImage(doubleBuffer, getInsets().left, getInsets().top, null);
	}
	
	private void paintGsPlaying(Graphics g) {
		for(KBackground bg : e.bgs)
			paintKRectangle(bg, g);
		for(KTree tree : e.trees)
			paintKTree(tree, g);
		for(KPipe pipe : e.pipes)
			paintKPipe(pipe, g);
		paintKRectangle(e.pajarito, g);
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(e.score), convertToPresentation(e.pajarito.posicion.x, e.pajarito.posicion.y, 0, 0)[0],
							convertToPresentation(e.pajarito.posicion.x, e.pajarito.posicion.y, 0, 0)[1] - 10);
	}
	private void paintKRectangle(KRectangle r, Graphics g) {
		int[] rectInfo = convertToPresentation(r.posicion.x, r.posicion.y, r.width, r.height);
	
		g.setColor(r.color);
		g.fillRect(rectInfo[0], rectInfo[1], rectInfo[2], rectInfo[3]);
	}
	private void paintKTree(KTree tree, Graphics g) {
		paintKRectangle(tree.copa, g);
		paintKRectangle(tree.tronco, g);
	}
	private void paintKPipe(KPipe pipe, Graphics g) {
		paintKRectangle(pipe.smallUR, g);
		paintKRectangle(pipe.smallDR, g);
		paintKRectangle(pipe.largeUR, g);
		paintKRectangle(pipe.largeDR, g);
	}
	
	private int[] convertToPresentation(double x, double y, double width, double height) {
		return new int[] { Double.valueOf(x * RATIOBETWCOORDSYSTEMS).intValue(),
				      Double.valueOf(y * RATIOBETWCOORDSYSTEMS).intValue(),
				      Double.valueOf(width * RATIOBETWCOORDSYSTEMS).intValue(),
				      Double.valueOf(height * RATIOBETWCOORDSYSTEMS).intValue()
			};
	}
}














