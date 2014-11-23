package net.bobmandude9889.Astroids;

public class Location {
	
	public double x;
	public double y;
	
	public Location(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double distanceFrom(Location pos){
		return Math.sqrt(Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2));
	}
	
	public Location add(double x, double y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Location plus(double x, double y){
		return new Location(this.x + x, this.y + y);
	}
	
}
