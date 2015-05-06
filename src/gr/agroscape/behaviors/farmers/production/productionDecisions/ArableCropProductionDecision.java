package gr.agroscape.behaviors.farmers.production.productionDecisions;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;

public class ArableCropProductionDecision extends AProductionDecision {
	
	private ArableCropCultivation decision;
	private Plot plot;
	
	private ArableCropProductionDecision(Plot p) {
		super(p);
		this.plot = p;
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
		//p.setAgriculturalLandUse(this.decision);		
	}

	@Override
	public String toString() {
		return "Plot: " + this.plot.toString() + " | Crop=" + this.decision.toString() + "\n";
	}

	public ArableCropCultivation getDecision() {
		return decision;
	}

	

	
	
}
