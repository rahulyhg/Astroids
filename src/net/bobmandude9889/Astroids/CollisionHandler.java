package net.bobmandude9889.Astroids;

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
			for (Moveable aM : display.astroidVelHandler.vel) {
				Astroid a = (Astroid) aM;
				for (Moveable bM : display.ship.bulletHandler.vel) {
					Bullet b = (Bullet) bM;
					if (b.getRect().intersects(a.getRect())) {
						display.astroids.remove(a);
						display.astroidVelHandler.vel.remove(a);
						display.ship.bulletHandler.vel.remove(b);
					}
				}
				if(a.getRect().intersects(display.ship.getRect())){
					display.astroids.remove(a);
					display.astroidVelHandler.vel.remove(a);
				}
			}
		}
	}

}
