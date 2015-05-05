package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.farmers.ABehavingFarmer;
import repast.simphony.engine.schedule.ScheduledMethod;

public class StupidoFarmer extends ABehavingFarmer<StupidoFarmer> implements IScheduledBehavior<StupidoFarmer>   {

	/**
	 * A reference to the container context
	 */
	protected StupidoFarmersContainer container;
	

	public StupidoFarmer(Farmer owner, StupidoFarmersContainer c) {
		super(owner);
		this.container=c;
	}

	private int stupidoProperty;
	

	
	@ScheduledMethod (start=2,interval = 2)
	public void setRandom() {
		this.stupidoProperty =  this.container.getRandom();
	}
	
	@ScheduledMethod (start=2,interval = 1)
	public void print() {
		System.err.println("Farmer, id="+this.owner.getID() + ", stupido random=" + this.stupidoProperty);
	}
	
	
	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return this;
	}

}
