package gr.agroscape.behaviors.farmers.stupido;

import java.util.Random;

import repast.simphony.engine.schedule.ScheduledMethod;
import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehavedObject;

public class StupidoFarmer extends ABehavedObject<Farmer> {

	private static Random random = new Random();
	private int stupidoProperty;
	
	
	public StupidoFarmer(Farmer owner) {
		super(owner);
	}

	
	@ScheduledMethod (interval = 2)
	public void setRandom() {
		this.stupidoProperty =  random.nextInt();
	}
	
	@ScheduledMethod (interval = 1)
	public void print() {
		System.err.println("Farmer, id="+this.owner.getID() + ", stupido random=" + this.stupidoProperty);
	}
	
	
	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return this;
	}

}
