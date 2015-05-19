package gr.agroscape.contexts;

import gr.agroscape.agents.Plot;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.collections15.Predicate;

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
	private GridValueLayer plotIdsValueLayer;
	

	/**
	 * A full constructor
	 * @param dataLoader
	 * @param valuelayers
	 * @param lpr
	 */
	public PlotsContext() {
		super("PlotsContext");
		this.setId("PlotsContext");
		plotIdsValueLayer = new GridValueLayer("PlotIds", 
												0, 
												true, 
												RunEnvironment.getInstance().getParameters().getInteger("gridWidth"),
												RunEnvironment.getInstance().getParameters().getInteger("gridHeight")
											);
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
		return this.findAdjacentPlots(p,1);
	}
	
	public ArrayList<Plot>findAdjacentPlots(Plot p, int buffer) {
		
		ArrayList<Integer> rInt = new ArrayList<>();
		
		int topY = p.getCorners().get(0).getY();
		int leftX = p.getCorners().get(0).getX();
		int bottomY = p.getCorners().get(1).getY();
		int rightX = p.getCorners().get(1).getX();
		
		//scan top edge
		for(int i=leftX;i<rightX;i++) {
			int y = (topY-buffer)>=0?topY-buffer:0;
			if(! rInt.contains((int)this.plotIdsValueLayer.get(i,y)) && !((int)this.plotIdsValueLayer.get(i,y)==p.getId())) rInt.add((int) this.plotIdsValueLayer.get(i,y));
		}
		
		//scan right edge
		for(int i=topY;i<bottomY;i++) {
			int x = (int) ((rightX+buffer)<=this.plotIdsValueLayer.getDimensions().getWidth()?rightX+buffer:this.plotIdsValueLayer.getDimensions().getWidth())-1;
			if(! rInt.contains((int)this.plotIdsValueLayer.get(x,i))  && !((int)this.plotIdsValueLayer.get(x,i)==p.getId())) rInt.add((int) this.plotIdsValueLayer.get(x,i));
		}
		
		//scan left edge
		for(int i=topY;i<bottomY;i++) {
			int x = (leftX-buffer)>=0?leftX-buffer:0;
			if(! rInt.contains((int)this.plotIdsValueLayer.get(x,i)) && !((int)this.plotIdsValueLayer.get(x,i)==p.getId()) ) rInt.add((int) this.plotIdsValueLayer.get(x,i));
		}
		
		
		//scan bottom edge
		for(int i=leftX;i<rightX;i++) {
			int y = (int) ((bottomY+buffer)<=this.plotIdsValueLayer.getDimensions().getHeight()?bottomY+buffer:this.plotIdsValueLayer.getDimensions().getHeight())-1;
			if(! rInt.contains((int)this.plotIdsValueLayer.get(i,y))  && !((int)this.plotIdsValueLayer.get(i,y)==p.getId()) ) rInt.add((int) this.plotIdsValueLayer.get(i,y));
		}
		
		ArrayList<Plot> r = new ArrayList<>();
		for (int plot_id : rInt) {
			if(plot_id>0) {			
				r.add(this.findPlotById(plot_id));
			}
		}
		
		//remove null
		r.removeAll(Collections.singleton(null));
		return r;
	}
	


	/*@Override
	public String toString() {
		return "Agrospace. width="+this.gridWidth +", height="+this.gridHeight
				+ "\nNumber of included Plots="+this.getObjects(Plot.class).size()
				+ "\nNumber of included Agents="+this.getObjects(Agent.class).size()
				+"\nLandPropertyRegistry: \n" + this.landPropertyRegistry.toString();
	}*/
	
	/**
	 * Search for a Plot with a specific id. 
	 * @param id
	 * @return
	 */
	public Plot findPlotById(int id) {
		Iterable<Plot> iff = this.query(new PredicatePlotId(id));
		for (Plot plot : iff) {
			return plot;
		}
		return null;
	}
	

	public String plotIdsStringMap() {
		String r="   ";
		for (int i = 0; i < this.plotIdsValueLayer.getDimensions().getWidth(); i++) {
			r += String.format("%2d.",  i);
		}
		r += "\n";
		
		for (int i = 0; i < this.plotIdsValueLayer.getDimensions().getWidth(); i++) {
			r +=  String.format("%2d.",  i);
			for (int j = 0; j < this.plotIdsValueLayer.getDimensions().getHeight(); j++) {
				r += String.format("%3d",  (int)this.plotIdsValueLayer.get(i,j));
			}
			r +="\n";
		}
		return r;
	}

	

} //end class


/**
 * 
 * @author Dimitris Kremmydas
 *
 */
class PredicatePlotId implements Predicate<Plot> {

	private int lookingFor;
	
	public PredicatePlotId(int lookingFor) {
		super();
		this.lookingFor = lookingFor;
	}

	@Override
	public boolean evaluate(Plot arg0) {
		if(arg0.getId()==lookingFor) return true;
		return false;
	}
	
}

