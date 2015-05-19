package gr.agroscape.contexts;

import gr.agroscape.agents.Plot;

import java.util.ArrayList;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.collections.IndexedIterable;
import repast.simphony.valueLayer.GridValueLayer;



public class PlotsContext extends DefaultContext<Plot> {

	
	/**
	 * Keeps a grid with the values of plot-id's on each cell.<br/>
	 * Very useful for quickly finding neighboring plots
	 */
	private GridValueLayer plotIdsValueLayer = new GridValueLayer("PlotIds", 
																	0, 
																	true, 
																	RunEnvironment.getInstance().getParameters().getInteger("gridWidth"),
																	RunEnvironment.getInstance().getParameters().getInteger("gridHeight")
																	);
	

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


	@Override
	protected void fireAddContextEvent(Plot o) {
		super.fireAddContextEvent(o);
		//update value layer accordingly
		ArrayList<GridPoint> points = o.getGridPoints();
		for (GridPoint p : points) {
			this.plotIdsValueLayer.set(o.getId(), p.getX(),p.getY());
		}
	}

	/**
	 * Get the adjacent plots for a given Plot.<br />
	 * It is using the {@link #plotIdsValueLayer} property of the context.
	 * @param p
	 * @return
	 */
	public ArrayList<Plot>findAdjacentPlots(Plot p) {
		
		
		return new ArrayList<>();
	}
	


	/*@Override
	public String toString() {
		return "Agrospace. width="+this.gridWidth +", height="+this.gridHeight
				+ "\nNumber of included Plots="+this.getObjects(Plot.class).size()
				+ "\nNumber of included Agents="+this.getObjects(Agent.class).size()
				+"\nLandPropertyRegistry: \n" + this.landPropertyRegistry.toString();
	}*/
	
	
	



}



