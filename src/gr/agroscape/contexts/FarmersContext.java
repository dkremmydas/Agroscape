package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<Farmer> {

		

	public FarmersContext() {
		super("FarmersContext");
	}
	
	

	/**
	 * Gets an ArrayList of all Farmers within context
	 * @return
	 */
	public ArrayList<Farmer> getFarmers() {
		
		ArrayList<Farmer> r = new ArrayList<Farmer>();
		Iterable<Farmer> list = this.getAgentLayer(Farmer.class);
		for (Farmer f : list) {
			r.add(f);
		}
		return r;
	}
	
	
	
	
	
}
