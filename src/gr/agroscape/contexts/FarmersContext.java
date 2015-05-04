package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<Farmer> {

	private Space space;
	
	
	public FarmersContext() {
		super("FarmersContext");
		this.setId("FarmersContext");
		this.space = Space.getInstance();
	}
	
	
	@SuppressWarnings("unchecked")
	public void attachBehavior(ABehaviorContainer<? extends Farmer> behaviorContainer) {
		this.addSubContext((Context<? extends Farmer>) behaviorContainer);
	}
	
	
	

	
}
	
	
	
	
