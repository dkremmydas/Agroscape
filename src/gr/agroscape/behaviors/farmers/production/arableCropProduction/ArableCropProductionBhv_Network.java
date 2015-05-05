package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.interfaces.AProductionDecision;

import java.util.ArrayList;
import java.util.Collection;

public class ArableCropProductionBhv_Network extends AArableCropProductionBhv {

	public ArableCropProductionBhv_Network(
			ArrayList<ArableCropCultivation> pC, long liquidity, Farmer f,
			ArableCropProductionBhvContext container) {
		super(pC, liquidity, f, container);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends AProductionDecision> makeProductionDecision(
			Collection<Plot> plots) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void calculateExpectations() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	

}
