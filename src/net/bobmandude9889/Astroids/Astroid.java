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
	
	List<Point> points;
	
	public Astroid(int type, int size, Location pos, Velocity vel){
		this.type = type;
		this.size = size;
		this.pos = pos;
		this.vel = vel;
		this.points = new ArrayList<Point>();
	}
	
	public void render(Graphics g){
		BufferedImage img = new BufferedImage(size,size,BufferedImage.TRANSLUCENT);
		img.getGraphics().fillOval((int) pos.x, (int) pos.y, size, size);
		for(int x = 0; x < img.getWidth()){
			for(int y = 0; y < img.getHeight()){
				if(/**Is Translucent */){
					
				}
			}
		}
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
