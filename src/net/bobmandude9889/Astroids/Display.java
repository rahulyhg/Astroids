package net.bobmandude9889.Astroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

public class Display extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	Frame frame;

	Thread thread = new Thread(this);

	long lastRotation = System.currentTimeMillis();

	Ship ship;

	VelocityHandler vh;

	int fps = 0;
	long lastFrame = System.currentTimeMillis();
	int frames = 0;

	CollisionHandler ch;

	public Display(Frame frame) {
		this.frame = frame;

		thread.start();
	}

	@Override
	public void run() {
		ship = new Ship(new Location(400, 300), this);
		vh = new VelocityHandler();
		vh.add(ship);

		frame.addKeyListener(ship);
		this.addMouseMotionListener(ship);
		this.addMouseListener(ship);

		vh.add(new Astroid(0,20,new Location(50,50), new Velocity(4,4)));
		
		/*for(int i = 0; i < 360; i++){
			ship.shoot(Math.toRadians(i));
		}*/
		
		ch = new CollisionHandler(this);

		while (true) {
			repaint();

			frames++;
			if (System.currentTimeMillis() - 1000 >= lastFrame) {
				lastFrame = System.currentTimeMillis();
				fps = frames;
				frames = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.WHITE);
		if (ship != null) {
			for (int i = 0; i < vh.vel.size(); i++) {
				Moveable m = vh.get(i);
				if (!m.isInScreen(frame.getSize()) && !isShip(m))
					vh.vel.remove(i);
				m.render(g);
			}
			g.drawString("Ship: ", 0, 30);
			g.drawString("Rotation = " + (ship.rotation * (180 / Math.PI)), 0, 40);
			g.drawString("Pos = " + ship.pos.x + " , " + ship.pos.y, 0, 50);
			g.drawString("Vel = " + ship.vel.x + " , " + ship.vel.y, 0, 60);
			if (vh.vel.size() > 0) {
				Bullet b = null;
				for (Moveable m : vh.vel) {
					try {
						b = (Bullet) m;
					} catch (Exception e) {
						b = null;
					}
				}
				if (b != null) {
					g.drawString("Most Recent Bullet: ", 0, 80);
					g.drawString("Pos = " + b.pos.x + " , " + b.pos.y, 0, 90);
					g.drawString("Vel = " + b.vel.x + " , " + b.vel.y, 0, 100);
				}
			}
		}
		g.drawString("FPS: " + fps, 0, 10);
	}
	
	public boolean isShip(Moveable m){
		try{
			Ship s = (Ship) m;
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
}
