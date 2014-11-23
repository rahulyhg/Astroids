package net.bobmandude9889.Astroids;

import java.awt.Dimension;
import java.awt.Graphics;


public interface Moveable {
	
	public Velocity getVel();
	public Location getPos();
	public void setPos(Location pos);
	public boolean isInScreen(Dimension screenSize);
	public void render(Graphics g);
	
}
