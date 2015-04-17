package gr.agroscape.contexts;

import gr.agroscape.behaviors.farmers.production.interfaces.IHasProductionAbility;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<IHasProductionAbility> {

		

	public FarmersContext() {
		super("FarmersContext");
	}
	
	

	/**
	 * Gets an ArrayList of all {@link ICropProducer} within context
	 * @return
	 */
	public ArrayList<IHasProductionAbility> getCropProducers() {
		
		ArrayList<IHasProductionAbility> r = new ArrayList<IHasProductionAbility>();
		Iterable<IHasProductionAbility> list = this.getAgentLayer(IHasProductionAbility.class);
		for (IHasProductionAbility f : list) {
			r.add(f);
		}
		return r;
	}
	
	
	
	
	
}
