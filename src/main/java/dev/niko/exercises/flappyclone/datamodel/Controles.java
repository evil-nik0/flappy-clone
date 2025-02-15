package dev.niko.exercises.flappyclone.datamodel;

import java.awt.event.*;

public class Controles implements KeyListener {

	public boolean spacePressed;
	
	public Controles() {
		spacePressed = false;
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				spacePressed = true;
				break;
			default:
				
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				spacePressed = false;
				break;
			default:
				
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
}
