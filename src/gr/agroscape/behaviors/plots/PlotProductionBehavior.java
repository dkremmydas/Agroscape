package gr.agroscape.behaviors.plots;

import java.util.Collection;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.AAgriculturalActivity;
import gr.agroscape.contexts.Space;

public class PlotProductionBehavior extends APlotBehavior {

	/**
	 * What {@link AAgriculturalActivity} is appointed for the plot ?
	 */
    private AAgriculturalActivity agriculturalLandUse = null;
	
	
	/**
	 * Constructor
	 * @param owner
	 */
	public PlotProductionBehavior(Plot owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void setUpOwner() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Getter for agriculturalLandUse 
	 * @return
	 */
	public AAgriculturalActivity getAgriculturalLandUse() {
		return agriculturalLandUse;
	}

	/**
	 * Setter for agriculturalLandUse
	 * @param landuse
	 */
	public void setAgriculturalLandUse(AAgriculturalActivity landuse) {
		this.agriculturalLandUse = landuse;
	}



	@Override
	public void setupSpace(Space s, IScheduledBehaviorDataLoader dl) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setUpOwners(Collection<Plot> owners,
			IScheduledBehaviorDataLoader dl) {
		// TODO Auto-generated method stub
		
	}
}
