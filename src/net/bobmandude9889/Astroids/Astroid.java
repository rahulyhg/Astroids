package net.bobmandude9889.Astroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Astroid implements Moveable {

	int type;
	int size;
	
	Location pos;
	Velocity vel;
	
	List<Point> points;
	
	BufferedImage img;
	
	public Astroid(int type, int size, Location pos, Velocity vel){
		this.type = type;
		this.size = size;
		this.pos = pos;
		this.vel = vel;
		this.points = new CopyOnWriteArrayList<Point>();
		img = new BufferedImage(size,size,BufferedImage.TYPE_4BYTE_ABGR);
		img.getGraphics().fillOval((int) pos.x, (int) pos.y, size, size);
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				int rgb = img.getRGB(x, y);
				if(rgb>>24 != 0x00){
					points.add(new Point(x,y));
				}
			}
		}
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
	
	@Override
	public boolean isInScreen(Dimension screenSize){
		return !((pos.x < 0 || pos.x > screenSize.width) || (pos.y < 0 || pos.y > screenSize.height)); 
	}
	
}
