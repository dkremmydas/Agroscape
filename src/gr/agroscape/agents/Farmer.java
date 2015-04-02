package gr.agroscape.agents;

import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class Farmer extends Agent implements ICropProducer {
	
    /**
     * The liquidity at the current moment (�cents)
     */
    protected long liquidity;
    
    protected ArrayList<Crop> potentialCrops = new ArrayList<Crop>();
    
    protected HashMap<Crop,Float> thisStepProduction = new HashMap<Crop, Float>();
    
	
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
	
	
	
	
	/**
	 * Getter for this Step's Production. It is an aggregate over all farmer's plots.
	 * @return
	 */
	public HashMap<Crop, Float> getThisStepProduction() {
		return thisStepProduction;
	}
	
	/**
	 * Add the given production HashMap to the total (current step's) production
	 * @param pr
	 */
	protected void aggregateProduction(HashMap<Crop, Float> pr) {

		for (Entry<Crop, Float> entry : pr.entrySet()) {
			Crop key = entry.getKey();
			Float value = entry.getValue();
			if(! this.thisStepProduction.containsKey(key)) this.thisStepProduction.put(key,0f);
			this.thisStepProduction.put(key,this.thisStepProduction.get(key)+value);
		}
			
	}

	abstract void calculateExpectations();
	

	
	
	
	
	
	
	
	
	
	

} //end class
