package net.bobmandude9889.Astroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Astroid implements Moveable {

	int type;
	int size;
	
	Location pos;
	Velocity vel;
	
	public Astroid(int type, int size, Location pos, Velocity vel){
		this.type = type;
		this.size = size;
		this.pos = pos;
		this.vel = vel;
	}
	
	public void render(Graphics g){
		g.fillOval((int) pos.x, (int) pos.y, size, size);
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

	public Rectangle getRect(){
		return new Rectangle(new Point((int) pos.x, (int) pos.y), new Dimension(size,size));
	}
	
}
