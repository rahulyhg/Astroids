package net.bobmandude9889.Astroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bullet implements Moveable{
	
	Velocity vel;
	Location pos;
	
	double speed = 10;
	
	int size = 4;
	
	List<Point> points;
	
	BufferedImage img;
	
	public Bullet(Ship ship){
		vel = new Velocity((Math.sin(ship.rotation) * speed), (Math.cos(ship.rotation) * speed));
		pos = new Location((Math.sin(ship.rotation) * (ship.original.getHeight() / 2)) + ship.pos.x, -((Math.cos(ship.rotation) * (ship.original.getHeight() / 2)) - ship.pos.y));
		vel.y = -vel.y;
		points = new CopyOnWriteArrayList<Point>();
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
	
	public Bullet(double rotation, Ship ship){
		vel = new Velocity((Math.sin(rotation) * speed), (Math.cos(rotation) * speed));
		pos = new Location((Math.sin(rotation) * (ship.original.getHeight() / 2)) + ship.pos.x, -((Math.cos(rotation) * (ship.original.getHeight() / 2)) - ship.pos.y));
		vel.y = -vel.y;
		points = new CopyOnWriteArrayList<Point>();
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
		g.setColor(Color.WHITE);
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
	
	@Override
	public boolean isInScreen(Dimension screenSize){
		return !((pos.x < 0 || pos.x > screenSize.width) || (pos.y < 0 || pos.y > screenSize.height)); 
	}
	
	public Rectangle getRect(){
		return new Rectangle(new Point((int) pos.x, (int) pos.y), new Dimension(size,size));
	}
	
}
