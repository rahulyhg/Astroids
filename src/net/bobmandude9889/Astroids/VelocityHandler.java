package net.bobmandude9889.Astroids;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VelocityHandler implements Runnable{
	
	List<Moveable> vel;
	
	Thread thread = new Thread(this);
	
	long lastMove = System.currentTimeMillis();
	
	public VelocityHandler(){
		vel = new CopyOnWriteArrayList<Moveable>();
		thread.start();
	}

	public void add(Moveable m){
		vel.add(m);
	}
	
	public Moveable get(int i){
		return vel.get(i);
	}
	
	@Override
	public void run() {
		while(true){
			if(System.currentTimeMillis() - 50 >= lastMove){
				for(Moveable m : vel){
					m.setPos(new Location(m.getPos().x + m.getVel().x,m.getPos().y + m.getVel().y));
				}
				lastMove = System.currentTimeMillis();
			}
		}
	}
	
}
