package net.bobmandude9889.Astroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Bullet implements Moveable{
	
	Velocity vel;
	Location pos;
	
	double speed = 10;
	
	public Bullet(Ship ship){
		vel = new Velocity((Math.sin(ship.rotation) * speed), (Math.cos(ship.rotation) * speed));
		pos = new Location((Math.sin(ship.rotation) * (ship.original.getHeight() / 2)) + ship.pos.x, -((Math.cos(ship.rotation) * (ship.original.getHeight() / 2)) - ship.pos.y));
		vel.y = -vel.y;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval((int) pos.x, (int) pos.y, 4, 4);
	}

	@Override
	public Velocity getVel() {
		return vel;
	}

	@Override
	public Location getPos() {
		return pos;
	}

	@Override
	public void setPos(Location pos) {
		this.pos = pos;
	}
	
	public boolean isInScreen(Dimension screenSize){
		return !((pos.x < 0 || pos.x > screenSize.width) || (pos.y < 0 || pos.y > screenSize.height)); 
	}
	
}
