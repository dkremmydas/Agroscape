package gr.agroscape.behaviors.farmers.production.expectations;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.contexts.Space;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpectedPlotCropYield extends AbstractExpectation<Plot, HashMap<ArableCropCultivation,Float>> {

	
	public ExpectedPlotCropYield(HashMap<Plot, HashMap<ArableCropCultivation,Float>> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedPlotCropYield(ArrayList<Plot> keys) {
		super(keys);
	}

	@Override
	HashMap<Plot, HashMap<ArableCropCultivation, Float>> getDefaultValues(ArrayList<Plot> plots) {
		HashMap<Plot, HashMap<ArableCropCultivation,Float>> v=new HashMap<Plot, HashMap<ArableCropCultivation,Float>>();
		for (Plot p: plots) {
       		HashMap<ArableCropCultivation,Float> tmp=new HashMap<ArableCropCultivation, Float>();
       		for(ArableCropCultivation c: Space.getAvailableCrops()) {
       			tmp.put(c, (float)p.getSuitability(c));
       			//tmp.put(c,1f);
       		}
       		v.put(p, tmp);
    	}
		return v;
	}

	
	
	

	

}
