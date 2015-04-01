package gr.agroscape.agents;

import gr.agroscape.agents.expectations.AbstractExpectation;
import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Farmer extends Agent implements ICropProducer {
	
    /**
     * The liquidity at the current moment (€cents)
     */
    protected long liquidity;
    
    protected ArrayList<Crop> potentialCrops = new ArrayList<Crop>();
    
	
	/**
	 * Constructor
	 * @param id
	 * @param grid
	 */

	
	
	public Farmer(ArrayList<Crop> pC, int id) {
		super(id);	
		this.potentialCrops=pC;
	}
 
	public Farmer(ArrayList<Crop> pC) {
		super();	
		this.potentialCrops=pC;
	}

	
	/**
	 * 
	 * @return float (hectares)
	 */
	public  float getTotalArableLand() {
		   float r=0;
	       ArrayList<Plot> myCultivationPlots = this.mainContext.getLandPropertyRegistry().getRentedPlots(this);
	       for(Plot v: myCultivationPlots) {r+=v.getArea();}
	       return r;
	}

	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Crop> getPotentialCrops() {
		return potentialCrops;
	}

	/**
	 * 
	 * @param potentialCrops
	 */
	public void setPotentialCrops(ArrayList<Crop> potentialCrops) {
		this.potentialCrops = potentialCrops;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Plot> getCultivatingPlots() {
		return this.mainContext.getLandPropertyRegistry().getCultivatingPlots(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public double getCultivatingPlotArea() {
		double r= 0d;
		ArrayList<Plot> ps = this.getCultivatingPlots();
		for (Iterator<Plot> iterator = ps.iterator(); iterator.hasNext();) {
			Plot plot = iterator.next();
			r += plot.getArea();
		}
		return r;
	}
	
	
	
	abstract void calculateExpectations();
	

	
	
	
	
	
	
	
	
	
	

} //end class
