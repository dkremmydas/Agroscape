package gr.agroscape.contexts;

import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhvContext;
import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;

public class BehaviorsContext extends DefaultContext<ABehaviorContext>  {

	
	/**
	 * A full constructor
	 * @param dataLoader
	 * @param valuelayers
	 * @param lpr
	 */
	public BehaviorsContext() {
		super("BehaviorsContext");
		this.setId("BehaviorsContext");
	}

	@Override
	public void addSubContext(Context<? extends ABehaviorContext> context) {
		super.addSubContext(context);
	}


	
	
	
	

} //end class



