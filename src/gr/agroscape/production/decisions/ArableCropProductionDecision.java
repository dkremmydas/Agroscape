package gr.agroscape.production.decisions;

import gr.agroscape.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.plot.Plot;

public class ArableCropProductionDecision extends ProductionDecision {
	
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
	public String toString() {
		return "Plot: " + this.plot.toString() + " | Crop=" + this.decision.toString() + "\n";
	}

	public ArableCropCultivation getDecision() {
		return decision;
	}

	

	
	
}
