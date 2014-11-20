package net.bobmandude9889.Astroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Display extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;

	Frame frame;
	
	Thread thread = new Thread(this);
	
	long lastRotation = System.currentTimeMillis();
	
	Ship ship;
	VelocityHandler shipVelHandler;
	
	List<Astroid> astroids;
	VelocityHandler astroidVelHandler;
	
	int fps = 0;
	long lastFrame = System.currentTimeMillis();
	int frames;
	
	CollisionHandler ch;
	
	public Display(Frame frame){
		this.frame = frame;
		
		thread.start();
	}

	@Override
	public void run() {
		ship = new Ship(new Location(400,300));
		shipVelHandler = new VelocityHandler();
		shipVelHandler.add(ship);
		
		frame.addKeyListener(ship);
		this.addMouseMotionListener(ship);
		this.addMouseListener(ship);
		
		astroids = new ArrayList<Astroid>();
		astroids.add(new Astroid(0,45,new Location(100,100), new Velocity(3,3)));
		astroidVelHandler = new VelocityHandler();
		astroidVelHandler.add(astroids.get(0));
		
		ch = new CollisionHandler(this);
		
		while(true){
			repaint();
			
			frames++;
			if(System.currentTimeMillis() - 1000 >= lastFrame){
				lastFrame = System.currentTimeMillis();
				fps = frames;
				frames = 0;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.WHITE);
		if(ship != null && astroids != null){
			ship.render(g);
			for(Astroid a : astroids){
				a.render(g);
			}
			for(int i = 0; i < ship.bulletHandler.vel.size(); i++){
				Bullet b = (Bullet) ship.bulletHandler.get(i);
				if(!b.isInScreen(frame.getSize())) ship.bulletHandler.vel.remove(i);
				b.render(g);
			}g.drawString("Ship: ", 0, 30);
			g.drawString("Rotation = " + (ship.rotation * (180 / Math.PI)), 0, 40);
			g.drawString("Pos = " + ship.pos.x + " , " + ship.pos.y, 0, 50);
			g.drawString("Vel = " + ship.vel.x + " , "+  ship.vel.y, 0, 60);
			if(ship.bulletHandler.vel.size() > 0){
				Bullet b = (Bullet) ship.bulletHandler.get(ship.bulletHandler.vel.size() - 1);
				g.drawString("Most Recent Bullet: ", 0, 80);
				g.drawString("Pos = " + b.pos.x + " , " + b.pos.y, 0, 90);
				g.drawString("Vel = " + b.vel.x + " , " + b.vel.y, 0, 100);
			}
		}
		g.drawString("FPS: " + fps, 0, 10);
	}
	
}
