package gr.agroscape.behaviors.farmers.production.interfaces;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;

public class ArableCropProductionDecision extends AProductionDecision {
	
	private ArableCropCultivation decision;
	
	
	private ArableCropProductionDecision(Plot p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public ArableCropProductionDecision(Plot p, ArableCropCultivation c) {
		this(p);
		this.decision = c;
	}

	

	@Override
	public void feedbackToFarmer(Farmer producers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedbackToPlot(Plot p) {
		p.setAgriculturalLandUse(this.decision);		
	}

	@Override
	public String toString() {
		return super.toString() + ": Crop=" + this.decision.toString();
	}

	//@Override
	//public Map<Product, Float> productionRealization(IWeatherSuitability w) {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

	
	
}
