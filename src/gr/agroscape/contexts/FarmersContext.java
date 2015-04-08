package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.ICropProducer;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<ICropProducer> {

		

	public FarmersContext() {
		super("FarmersContext");
	}
	
	

	/**
	 * Gets an ArrayList of all {@link ICropProducer} within context
	 * @return
	 */
	public ArrayList<ICropProducer> getCropProducers() {
		
		ArrayList<ICropProducer> r = new ArrayList<ICropProducer>();
		Iterable<ICropProducer> list = this.getAgentLayer(ICropProducer.class);
		for (ICropProducer f : list) {
			r.add(f);
		}
		return r;
	}
	
	
	
	
	
}
