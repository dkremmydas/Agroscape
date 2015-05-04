package gr.agroscape.contexts;

import gr.agroscape.agents.Plot;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;
import repast.simphony.util.collections.IndexedIterable;



public class PlotsContext extends DefaultContext<Plot> {



	/**
	 * A full constructor
	 * @param dataLoader
	 * @param valuelayers
	 * @param lpr
	 */
	public PlotsContext() {
		super("PlotsContext");
		this.setId("PlotsContext");
	}
	
	
	public ArrayList<Plot> getAvailablePlots() {
		ArrayList<Plot> r = new ArrayList<Plot>();
		
		IndexedIterable<Plot> plots = this.getObjects(Plot.class);
		for (Plot plot : plots) {
			r.add(plot);
		}
		
		return r;
	}




	/*@Override
	public String toString() {
		return "Agrospace. width="+this.gridWidth +", height="+this.gridHeight
				+ "\nNumber of included Plots="+this.getObjects(Plot.class).size()
				+ "\nNumber of included Agents="+this.getObjects(Agent.class).size()
				+"\nLandPropertyRegistry: \n" + this.landPropertyRegistry.toString();
	}*/
	
	
	



}



