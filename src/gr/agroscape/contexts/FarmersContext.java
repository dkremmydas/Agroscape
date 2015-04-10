package gr.agroscape.contexts;

import gr.agroscape.production.IhasProductionAbility;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<IhasProductionAbility> {

		

	public FarmersContext() {
		super("FarmersContext");
	}
	
	

	/**
	 * Gets an ArrayList of all {@link ICropProducer} within context
	 * @return
	 */
	public ArrayList<IhasProductionAbility> getCropProducers() {
		
		ArrayList<IhasProductionAbility> r = new ArrayList<IhasProductionAbility>();
		Iterable<IhasProductionAbility> list = this.getAgentLayer(IhasProductionAbility.class);
		for (IhasProductionAbility f : list) {
			r.add(f);
		}
		return r;
	}
	
	
	
	
	
}
