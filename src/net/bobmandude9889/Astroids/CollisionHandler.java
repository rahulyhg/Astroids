package net.bobmandude9889.Astroids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler implements Runnable {

	Display display;

	Thread thread = new Thread(this);

	public CollisionHandler(Display display) {
		this.display = display;

		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			List<Astroid> astroids = new ArrayList<Astroid>();
			List<Bullet> bullets = new ArrayList<Bullet>();
			for (Moveable m : display.vh.vel) {
				try {
					Astroid a = (Astroid) m;
					astroids.add(a);
				} catch (Exception e) {
					try {
						Bullet b = (Bullet) m;
						bullets.add(b);
					} catch (Exception e1) {}
				}
			}
			
			for(Astroid a : astroids){
				Ship ship = display.ship;
				double shipAstroidDistance = ship.pos.distanceFrom(a.pos.plus(a.size / 2, a.size / 2));
				if(shipAstroidDistance < (a.size / 2) + (display.ship.size / 2)){
					System.out.println("Ship and astroid colide " + shipAstroidDistance);
					display.vh.vel.remove(a);
				}
				
				for(Bullet b : bullets){
					double bulletAstroidDistance = b.pos.plus(b.size / 2, b.size / 2).distanceFrom(a.pos.plus(a.size / 2, a.size / 2));
					if(bulletAstroidDistance < (a.size / 2) + (b.size / 2)){
						System.out.println("Bullet and Astroid collide " + bulletAstroidDistance);
						display.vh.vel.remove(b);
						display.vh.vel.remove(a);
					}
				}
			}
		}
	}

	public boolean containsSimilarPoint(List<Point> list, Point p, int xOff, int yOff) {
		for (Point p1 : list) {
			p1.x += xOff;
			p1.y += yOff;
			if (p1.x == p.x && p1.y == p.y) {
				return true;
			}
		}
		return false;
	}

}
