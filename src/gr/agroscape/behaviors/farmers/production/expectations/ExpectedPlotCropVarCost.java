package gr.agroscape.behaviors.farmers.production.expectations;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhvContext;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The expectation regarding the variable cost for each Crop in each Plot (Euro/h)
 * @author jkr
 *
 */
public class ExpectedPlotCropVarCost extends AbstractExpectation<Plot, HashMap<ArableCropCultivation,Long>> {

	
	public ExpectedPlotCropVarCost(HashMap<Plot, HashMap<ArableCropCultivation,Long>> values) {
		super(values);
	}
	
	public ExpectedPlotCropVarCost(ArrayList<Plot> keys) {
		super(keys);
	}

	/**
	 * For each Plot and Crop the default VarCost is 50*(1+random(50))
	 */
	@Override
	HashMap<Plot, HashMap<ArableCropCultivation, Long>> getDefaultValues(ArrayList<Plot> plots) {
		
		ArableCropProductionBhvContext acpc = (ArableCropProductionBhvContext) SimulationContext.getInstance().findContext("ArableCropProductionBehavior");
		ArrayList<ArableCropCultivation> availableCrops = acpc.getAvailableCrops();
		
		
		
		HashMap<Plot, HashMap<ArableCropCultivation,Long>> v=new HashMap<Plot, HashMap<ArableCropCultivation,Long>>();
		Random r = new Random();
		
		for (Plot p: plots) {
			HashMap<ArableCropCultivation,Long> tmp=new HashMap<ArableCropCultivation, Long>();
       		for(ArableCropCultivation c: availableCrops) {
       			tmp.put(c, (long)(1*(1+r.nextInt(50))));
       			//tmp.put(c, 50l);
       		}
       		v.put(p, tmp);
    	}
		return v;
	}
	
	
	
	

	

}
