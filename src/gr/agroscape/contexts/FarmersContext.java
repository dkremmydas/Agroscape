package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<Farmer> {


	
	public FarmersContext() {
		super("FarmersContext");
		this.setId("FarmersContext");
	}
	
	
	@SuppressWarnings("unchecked")
	public void attachBehavior(ABehaviorContext<? extends AFarmerBehavior<?>> behaviorContainer) {
		this.addSubContext((Context<? extends Farmer>) behaviorContainer);
	}
	
	
	

	
}
	
	
	
	
