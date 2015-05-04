package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.farmers.ABehavingFarmer;

import java.util.Random;

import repast.simphony.engine.schedule.ScheduledMethod;

public class StupidoFarmer extends ABehavingFarmer<StupidoFarmer> implements IScheduledBehavior<StupidoFarmer>   {

	
	
	public StupidoFarmer(Farmer owner) {
		super(owner);
		this.setRandom();
	}

	private static Random random = new Random();
	private int stupidoProperty;
	

	
	@ScheduledMethod (start=0,interval = 2)
	public void setRandom() {
		this.stupidoProperty =  random.nextInt();
	}
	
	@ScheduledMethod (start=0,interval = 1)
	public void print() {
		System.err.println("Farmer, id="+this.owner.getID() + ", stupido random=" + this.stupidoProperty);
	}
	
	
	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return this;
	}

}
