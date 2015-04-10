package gr.agroscape.agents.expectations;

import gr.agroscape.agents.Plot;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.landUse.ArableCrop;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpectedPlotCropYield extends AbstractExpectation<Plot, HashMap<ArableCrop,Float>> {

	
	public ExpectedPlotCropYield(HashMap<Plot, HashMap<ArableCrop,Float>> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedPlotCropYield(ArrayList<Plot> keys) {
		super(keys);
	}

	@Override
	HashMap<Plot, HashMap<ArableCrop, Float>> getDefaultValues(ArrayList<Plot> plots) {
		HashMap<Plot, HashMap<ArableCrop,Float>> v=new HashMap<Plot, HashMap<ArableCrop,Float>>();
		for (Plot p: plots) {
       		HashMap<ArableCrop,Float> tmp=new HashMap<ArableCrop, Float>();
       		for(ArableCrop c: MainContext.getAvailableCrops()) {
       			tmp.put(c, (float)p.getSuitability(c));
       			//tmp.put(c,1f);
       		}
       		v.put(p, tmp);
    	}
		return v;
	}

	
	
	

	

}
