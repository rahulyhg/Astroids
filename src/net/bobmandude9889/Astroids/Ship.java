package net.bobmandude9889.Astroids;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Ship implements MouseMotionListener, MouseListener, KeyListener, Moveable{

	Location pos;
	
	double rotation = 0;
	double h = 0;
	
	public Velocity vel;
	
	int speed = 6;
	
	BufferedImage original;
	BufferedImage image;
	
	ClassLoader cl = this.getClass().getClassLoader();
	
	long lastRotation = System.currentTimeMillis();
	
	int size = 50;
	
	VelocityHandler bulletHandler;
	
	Point mousePos;
	
	public Ship(Location location){
		this.pos = location;
		this.original = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
		original.getGraphics().drawImage(new ImageIcon(cl.getResource("assets/ship.png")).getImage(),0,0,size,size,null);
		this.image = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
		this.image.getGraphics().drawImage(original,0,0,null);
		bulletHandler = new VelocityHandler();
		vel = new Velocity(0,0);
		mousePos = new Point(0,0);
	}
	
	public void updateRotation(){
		Point p = (Point) mousePos.clone();
		Location loc = new Location(pos.x,pos.y);
		p.x -= 400;
		p.y = -(p.y - 300);
		loc.x -= 400;
		loc.y = -(loc.y - 300);
		
		p.x = (int) (p.x - loc.x);
		p.y = (int) (p.y - loc.y);
		
		boolean flip = p.y < 0;
		
		h = Math.sqrt(Math.pow(p.x,2) + Math.pow(p.y,2));
		rotation = Math.asin(p.x / h);
		
		if(flip){
			 rotation += 2 * (Math.toRadians(90) - rotation);
		}
		
		if(rotation < 0){
			rotation = Math.toRadians(360) + rotation;
		}
		
		AffineTransform rt = AffineTransform.getRotateInstance(rotation,size / 2,size / 2);
		AffineTransformOp rtOp = new AffineTransformOp(rt,AffineTransformOp.TYPE_BILINEAR);
		image = rtOp.filter(original, null);
	}
	
	public void render(Graphics g){
		g.drawImage(image,(int) (pos.x - (size / 2)),(int) (pos.y - (size / 2)),null);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getPoint();
		updateRotation();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		bulletHandler.add(new Bullet(this));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case 65:
			vel.x = -speed;
			break;
		case 87:
			vel.y = -speed;
			break;
		case 68:
			vel.x = speed;
			break;
		case 83:
			vel.y = speed;
			break;
		default:
			break;
		}
		updateRotation();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == 65 || c == 68){
			vel.x = 0;
		} else if(c == 87 || c == 83){
			vel.y = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
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
		updateRotation();
		this.pos = pos;
	}
	
}
