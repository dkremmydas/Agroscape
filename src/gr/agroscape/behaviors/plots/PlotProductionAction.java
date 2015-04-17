package gr.agroscape.behaviors.plots;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.AAgriculturalActivity;

public class PlotProductionAction extends APlotAction {

	
    private AAgriculturalActivity agriculturalLandUse;
	
	
	
	public PlotProductionAction(Plot owner) {
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

	public AAgriculturalActivity getAgriculturalLandUse() {
		return agriculturalLandUse;
	}

	public void setAgriculturalLandUse(AAgriculturalActivity landuse) {
		this.agriculturalLandUse = landuse;
	}
}
