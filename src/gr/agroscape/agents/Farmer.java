package gr.agroscape.agents;

import gr.agroscape.agriculturalActivity.ArableCropCultivation;
import gr.agroscape.production.IHasProductionAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * A Farmer agent of Agroscape. <p />
 * <p>This is the abstract class that gives some basic functionality:
 *  <ul>
 *  <li>Every farmer has some common properties:
 *  <ul>
 *  
 *  <li>{@link #liquidity Liquidity} (synonym for working capital)</li>
 *  <li>{@link #potentialCrops Potential Crops}, since some farmers might not even consider 
 *  cultivating some of the crops considered in the simulation</li>
 *  <li>thisStepProduction 
 *  //TODO decide if this class is a good place  for the property to be
 *  </li>
 *  </ul>
 *  </li>
 *  
 *  <li>There are also here some helper functions:</li>
 *  <ul>
 *  <li>Get a List of Cultivating (owned+rented) Plots ({@link #getCultivatingPlots()})</li>
 *  <li>Calculate the Cultivating Plot Area ({@link #getCultivatingPlotArea()})</li>
 *  <li>Add a production to the current thisStepProduction ({@link #aggregateProduction(HashMap)})</li>
 *  </ul>
 *  
 *  </ul>
 *  </p>
 *  <p></p>
 * @author Dimitris Kremmydas
  */
public abstract class Farmer extends Agent implements IHasProductionAbility {
	
    /**
     * The liquidity at the current moment (�cents)
     */
    protected long liquidity;
    
    protected ArrayList<ArableCropCultivation> potentialAgriculturalActivity = new ArrayList<ArableCropCultivation>();
    
   
	
	/**
	 * Constructor
	 * @param id
	 * @param grid
	 */	
	public Farmer(ArrayList<ArableCropCultivation> pC, int id) {
		super(id);	
		this.potentialAgriculturalActivity=pC;
	}
 
	public Farmer(ArrayList<ArableCropCultivation> pC) {
		super();	
		this.potentialAgriculturalActivity=pC;
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
	public ArrayList<ArableCropCultivation> getPotentialAgriculturalActivity() {
		return potentialAgriculturalActivity;
	}

	/**
	 * 
	 * @param potentialCrops
	 */
	public void setPotentialAgriculturalActivity(ArrayList<ArableCropCultivation> potentialCrops) {
		this.potentialAgriculturalActivity = potentialCrops;
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
