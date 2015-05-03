package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

import java.util.Random;

import repast.simphony.engine.schedule.ScheduledMethod;

public class StupidoFarmer extends Farmer implements IScheduledBehavior<StupidoFarmer>   {

	private static Random random = new Random();
	private int stupidoProperty;
	Farmer owner;
	
	
	public StupidoFarmer(Farmer owner) {
		this.owner = owner;
	}

	
	@ScheduledMethod (start=1,interval = 2)
	public void setRandom() {
		this.stupidoProperty =  random.nextInt();
	}
	
	@ScheduledMethod (start=1,interval = 1)
	public void print() {
		System.err.println("Farmer, id="+this.owner.getID() + ", stupido random=" + this.stupidoProperty);
	}
	
	
	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return this;
	}

}
