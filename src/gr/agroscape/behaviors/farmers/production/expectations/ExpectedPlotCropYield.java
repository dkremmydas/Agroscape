package gr.agroscape.behaviors.farmers.production.expectations;

import gr.agroscape.agents.plot.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhvContext;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.HashMap;

import repast.simphony.valueLayer.GridValueLayer;


public class ExpectedPlotCropYield extends AbstractExpectation<Plot, HashMap<ArableCropCultivation,Float>> {

	
	public ExpectedPlotCropYield(HashMap<Plot, HashMap<ArableCropCultivation,Float>> values) {
		super(values);
	}
	
	public ExpectedPlotCropYield(ArrayList<Plot> keys) {
		super(keys);
	}

	@Override
	HashMap<Plot, HashMap<ArableCropCultivation, Float>> getDefaultValues(ArrayList<Plot> plots) {
		
		ArableCropProductionBhvContext acpc = (ArableCropProductionBhvContext) SimulationContext.getInstance().findContext("ArableCropProductionBehavior");
		HashMap<ArableCropCultivation, GridValueLayer> gvl = acpc.getCropSuitabilities();
		this.compute(gvl, plots);
		return this.values;
		
	}
	
	public void compute(HashMap<ArableCropCultivation, GridValueLayer> gvl, ArrayList<Plot> plots) {
		this.values = new HashMap<>();
		ArrayList<ArableCropCultivation> availableCrops = new ArrayList<>(gvl.keySet());
		for (Plot p: plots) {
			HashMap<ArableCropCultivation,Float> tmp=new HashMap<ArableCropCultivation, Float>();
			for(ArableCropCultivation c: availableCrops) {
				Float avg = (float) p.getAverage(gvl.get(c));
				tmp.put(c, avg);
			}
			this.values.put(p, tmp);
		}
	
	} //end method

	
	
	

	

}
