package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.human.Farmer;
import gr.agroscape.agents.plot.Plot;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.interfaces.IHasProductionAbility;
import gr.agroscape.behaviors.farmers.production.productionDecisions.ArableCropProductionDecision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * The behavior of a {@link Farmer} that produces arable crops <p />
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
public abstract class AArableCropProductionBhv extends AFarmerBehavior<AArableCropProductionBhv> implements IHasProductionAbility,IScheduledBehavior<AArableCropProductionBhv> {
	
	/**
	 * A reference to the container context
	 */
	protected ArableCropProductionBhvContext container;
	
    /**
     * The liquidity at the current moment (�cents)
     */
    protected long liquidity;
    
    /**
     * Potential Crops that he can cultivate
     */
    protected ArrayList<ArableCropCultivation> potentialAgriculturalActivity = new ArrayList<ArableCropCultivation>();
    
    /**
     * What was his last production decisions ?
     */
    protected ArrayList<ArableCropProductionDecision> lastProductionDecisions;

    
    
	/**
	 * Constructor
	 * @param id
	 * @param grid
	 */	
    private AArableCropProductionBhv(Farmer f,ArableCropProductionBhvContext container) {
    	super(f);
    	this.container = container;
    }
    
	public AArableCropProductionBhv(ArrayList<ArableCropCultivation> pC, long liquidity , Farmer f,ArableCropProductionBhvContext container) {
		this(f,container);
		this.potentialAgriculturalActivity=pC;
		this.liquidity = liquidity;
	}

	
	/**
	 * 
	 * @return float (hectares)
	 */
	public  float getTotalArableLand() {
		   float r=0;
	       ArrayList<Plot> myCultivationPlots = this.owner.getMainContext().getLandPropertyRegistry().getRentedPlots(this.owner);
	       for(Plot v: myCultivationPlots) {r+=v.getArea();}
	       return r;
	}


	/**
	 * 
	 * @return
	 */
	public ArrayList<ArableCropCultivation> getPotentialCrops() {
		return potentialAgriculturalActivity;
	}

	/**
	 * 
	 * @param potentialCrops
	 */
	public void setPotentialCrops(ArrayList<ArableCropCultivation> potentialCrops) {
		this.potentialAgriculturalActivity = potentialCrops;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Plot> getCultivatingPlots() {
		return this.owner.getMainContext().getLandPropertyRegistry().getCultivatingPlots(this.owner);
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
	
	
	
	@Override
	public Object getAnnotatedClass() {
		return this;
	}    

	
	
	/**
	 * A helper function to calculate the ArableCropCultivation that is most popular ArableCropCultivation, weighted by plot area 
	 * given an array of {@link ArableCropProductionDecision}s
	 * @param pd
	 * @return
	 */
	protected static  ArableCropCultivation findMostPopular(ArrayList<ArableCropProductionDecision> pd) {
			ArableCropCultivation r = null;
			HashMap<ArableCropCultivation,Double> popularity = new HashMap<>();
			
			//construct popularity (weighted by plot area) and find maximum (together)		
			for (ArableCropProductionDecision dec : pd) {
				if(dec!=null) {
					if(popularity.containsKey(dec.getDecision())) 
						popularity.put(dec.getDecision(), popularity.get(dec.getDecision())+dec.getPlot().getArea());
					else
						popularity.put(dec.getDecision(), dec.getPlot().getArea());
					
					if(r==null){r=dec.getDecision();}
					if(popularity.get(r).compareTo(popularity.get(dec.getDecision()))<0) {
						r = dec.getDecision();
					}
				}				
			}
			
			return r;
		}
	

	
	public double getTotalWheat() {return getArea(this.container.getAvailableCrops().get(1));}
	public double getTotalMaize() {return getArea(this.container.getAvailableCrops().get(0));}
	public double getTotalCotton() {return getArea(this.container.getAvailableCrops().get(2));}
	private int getArea(ArableCropCultivation c) {
		int r = 0;
		ArrayList<ArableCropProductionDecision> dcs = this.lastProductionDecisions;
		for (ArableCropProductionDecision d : dcs) {
			if(d.getDecision().equals(c)) {
				r+=(int)d.getPlot().getArea();
			}
		}
		return r;
	}

}


