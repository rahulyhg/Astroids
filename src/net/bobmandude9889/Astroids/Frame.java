package net.bobmandude9889.Astroids;

import java.awt.Cursor;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Frame();
	}

	@SuppressWarnings("deprecation")
	public Frame(){
		new JFrame();
		
		this.setTitle("Astroids");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setCursor(Cursor.CROSSHAIR_CURSOR);
		
		Display display = new Display(this);
		this.add(display);
	}
	
}
